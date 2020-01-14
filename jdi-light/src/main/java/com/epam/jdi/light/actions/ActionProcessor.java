package com.epam.jdi.light.actions;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.light.elements.interfaces.base.IBaseElement;
import com.epam.jdi.tools.func.JFunc1;
import com.epam.jdi.tools.map.MapArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.epam.jdi.light.actions.ActionHelper.ACTION_FAILED;
import static com.epam.jdi.light.actions.ActionHelper.AFTER_JDI_ACTION;
import static com.epam.jdi.light.actions.ActionHelper.AFTER_STEP_ACTION;
import static com.epam.jdi.light.actions.ActionHelper.BEFORE_JDI_ACTION;
import static com.epam.jdi.light.actions.ActionHelper.BEFORE_STEP_ACTION;
import static com.epam.jdi.light.actions.ActionHelper.fillTemplate;
import static com.epam.jdi.light.actions.ActionHelper.getJpClass;
import static com.epam.jdi.light.actions.ActionHelper.getJpMethod;
import static com.epam.jdi.light.actions.ActionOverrideUtils.getOverrideAction;
import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.Exceptions.safeException;
import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.elements.base.OutputTemplatesUtils.FAILED_ACTION_TEMPLATE;
import static com.epam.jdi.light.settings.TimeoutSettingsUtils.TIMEOUT;
import static com.epam.jdi.tools.LinqUtils.where;
import static com.epam.jdi.tools.ReflectionUtils.isInterface;
import static com.epam.jdi.tools.StringUtils.LINE_BREAK;
import static com.epam.jdi.tools.StringUtils.msgFormat;
import static com.epam.jdi.tools.Timer.nowTime;
import static com.epam.jdi.tools.map.MapArray.map;
import static com.epam.jdi.tools.pairs.Pair.$;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

@SuppressWarnings("unused")
@Aspect
public class ActionProcessor {

    public static MapArray<String, JFunc1<Object, Boolean>> CONDITIONS = map(
            $("", result -> true),
            $("true", result -> result instanceof Boolean && (Boolean) result),
            $("false", result -> result instanceof Boolean && !(Boolean) result),
            $("not empty", result -> result instanceof List && ((List) result).size() > 0),
            $("empty", result -> result instanceof List && ((List) result).size() == 0)
    );

    @Pointcut("execution(* *(..)) && @annotation(com.epam.jdi.light.common.JDIAction)")
    protected void jdiPointcut() { // empty
    }

    @Pointcut("execution(* *(..)) && @annotation(io.qameta.allure.Step)")
    protected void stepPointcut() { // empty
    }

    @Around("jdiPointcut()")
    public Object jdiAround(ProceedingJoinPoint jp) {
        try {
            if (aroundCount() > 1)
                return defaultAction(jp);
            BEFORE_JDI_ACTION.execute(jp);
            Object result = stableAction(jp);
            if (aroundCount() == 1)
                getDriver().manage().timeouts().implicitlyWait(TIMEOUT.get(), TimeUnit.SECONDS);
            return AFTER_JDI_ACTION.execute(jp, result);
        } catch (Throwable ex) {
            if(ex.getCause() instanceof AssertionError){
                throw new AssertionError();
            }
            throw exception(ex, ACTION_FAILED.execute(getObjAround(jp), (aroundCount() > 1) ? null : jp, getExceptionAround(ex, aroundCount() == 1)));
        }
    }

    public static Object getObjAround(ProceedingJoinPoint jp) {
        return jp.getThis() != null ? jp.getThis() : new Object();
    }

    public static String getExceptionAround(Throwable ex, boolean time) {
        String result = safeException(ex);
        while (result.contains("\n\n"))
            result = result.replaceFirst("\\n\\n", LINE_BREAK);
        result = result.replace("java.lang.RuntimeException:", "").trim();
        if (aroundCount() == 1)
            result = "[" + nowTime("mm:ss.S") + "] " + result.replaceFirst("\n", "");
        return result;
    }

    public static int aroundCount() {
        return where(currentThread().getStackTrace(),
                s -> s.getMethodName().equals("jdiAround")/* ||
                s.getClassName().equals("io.qameta.allure.aspects.StepsAspects")*/)
                .size();
    }

    public static Object defaultAction(ProceedingJoinPoint jp) throws Throwable {
        JDIBase obj = getJdi(jp);
        JFunc1<JDIBase, Object> overrideAction = getOverride(jp, obj);
        return overrideAction != null
                ? overrideAction.execute(obj) : jp.proceed();
    }

    public static JDIBase getJdi(ProceedingJoinPoint jp) {
        try {
            return jp.getThis() != null && isInterface(getJpClass(jp), IBaseElement.class)
                    ? ((IBaseElement) jp.getThis()).base() : null;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object stableAction(ProceedingJoinPoint jp) {
        String exceptionMsg = "";
        JDIAction ja = getJpMethod(jp).getMethod().getAnnotation(JDIAction.class);
        JDIBase obj = getJdi(jp);
        JFunc1<JDIBase, Object> overrideAction = getOverride(jp, obj);
        int timeout = getTimeout(ja, obj);
        long start = currentTimeMillis();
        Throwable exception = null;
        do {
            try {
                Object result = overrideAction != null
                        ? overrideAction.execute(obj) : jp.proceed();
                if (!condition(jp)) continue;
                return result;
            } catch (Throwable ex) {
                exception = ex;
                try {
                    exceptionMsg = safeException(ex);
                    Thread.sleep(200);
                } catch (Exception ignore) {
                }
            }
        } while (currentTimeMillis() - start < timeout * 1000);
        throw exception(exception, getFailedMessage(jp, exceptionMsg));
    }

    private static JFunc1<JDIBase, Object> getOverride(ProceedingJoinPoint jp, JDIBase obj) {
        return obj != null ? getOverrideAction(jp) : null;
    }

    private static int getTimeout(JDIAction ja, IBaseElement obj) {
        return ja != null && ja.timeout() != -1
                ? ja.timeout()
                : obj != null
                ? obj.base().getTimeout()
                : TIMEOUT.get();
    }

    private static String getFailedMessage(ProceedingJoinPoint jp, String exception) {
        MethodSignature method = getJpMethod(jp);
        try {
            String result = msgFormat(FAILED_ACTION_TEMPLATE, map(
                    $("exception", exception),
                    $("timeout", getTimeout(jp)),
                    $("action", method.getMethod().getName())
            ));
            return fillTemplate(result, jp, method);
        } catch (Exception ex) {
            throw exception(ex, "Surround method issue: " +
                    "Can't get failed message: " + safeException(ex));
        }
    }

    private static int getTimeout(ProceedingJoinPoint jp) {
        return getTimeout(null, getJdi(jp));
    }

    private static String getConditionName(ProceedingJoinPoint jp) {
        JDIAction ja = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(JDIAction.class);
        return ja != null ? ja.condition() : "";
    }

    private static boolean condition(ProceedingJoinPoint jp) {
        String conditionName = getConditionName(jp);
        return CONDITIONS.has(conditionName)
                ? CONDITIONS.get(conditionName).execute(jp)
                : true;
    }

    @Around("stepPointcut()")
    public Object stepAround(ProceedingJoinPoint jp) {
        try {
            BEFORE_STEP_ACTION.execute(jp);
            Object result = jp.proceed();
            return AFTER_STEP_ACTION.execute(jp, result);
        } catch (Throwable ex) {
            Object element = jp.getThis() != null ? jp.getThis() : new Object();
            throw exception(ex, ACTION_FAILED.execute(element, jp, safeException(ex)));
        }
    }
}
