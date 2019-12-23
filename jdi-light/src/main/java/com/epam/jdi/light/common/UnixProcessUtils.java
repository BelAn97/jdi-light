package com.epam.jdi.light.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.tools.StringUtils.inputStreamToList;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class UnixProcessUtils {
    /**
     * @param rootNamePart
     */
    public static void killProcessesTree(String rootNamePart) {
        List<String> chrome;
        try {
            chrome = getPIDsByNamePart(rootNamePart);
            logger.info("!!! " + chrome);
            for (String s : chrome) {
                int pid = Integer.parseInt(s);
                killChildProcesses(pid);
            }
            killProcessesByNamePart(rootNamePart);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Can't kill drivers");
        }
    }

    /**
     *
     * @param value
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private static List<String> getPIDsByNamePart(String value) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("ps -aux | grep " + value);
        process.waitFor();
        return inputStreamToList(process.getInputStream());
    }

    /**
     *
     * @param pid
     */
    private static void killChildProcesses(int pid) {
        String ppid = String.valueOf("-P "+pid);
        killProcessWithArgs(Collections.singletonList(ppid));
    }

    /**
     *
     * @param name
     */
    public static void killProcessesByNamePart(String name) {
        killProcessWithArgs(Arrays.asList("-aif", name));
    }

    /**
     *
     * @param args
     */
    private static void killProcessWithArgs(List<String> args) {
        List<String> list = new ArrayList<>();
        list.add("/usr/bin/pkill");
        list.addAll(args);
        try {
            Process child = new ProcessBuilder(list.toArray(new String[list.size()])).start();
            child.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Can't kill process: " + e.getMessage());
        }
    }

}