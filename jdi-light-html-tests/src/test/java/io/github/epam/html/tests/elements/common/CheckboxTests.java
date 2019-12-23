package io.github.epam.html.tests.elements.common;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.epam.jdi.light.common.UnixProcessUtils.getPIDsByNamePart;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static io.github.com.StaticSite.html5Page;
import static io.github.com.pages.HtmlElementsPage.acceptConditions;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedIn;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.testng.Assert.*;

/**
 * Created by Roman Iovlev on 19.08.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class CheckboxTests implements TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        html5Page.shouldBeOpened();
        acceptConditions.check();
    }

    @Test
    public void getLabelTextTest() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("ps -aux | grep java");
        BufferedReader r =  new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while((line=r.readLine())!=null) {
            logger.info("!!! " + line);
        }
        assertEquals(acceptConditions.labelText(),
            "Accept terms and conditions");
    }

    @Test
    public void checkTest() {
        acceptConditions.check();
        assertEquals(acceptConditions.isSelected(), true);
    }
    @Test
    public void uncheckTest() {
        acceptConditions.uncheck();
        assertEquals(acceptConditions.isSelected(), false);
    }

    @Test
    public void clickTest() {
        assertTrue(acceptConditions.isSelected());
        acceptConditions.click();
        assertFalse(acceptConditions.isSelected());
    }

    @Test
    public void isValidationTest() {
        acceptConditions.is().selected();
        acceptConditions.click();
        acceptConditions.is().deselected();
        acceptConditions.is().enabled();
        acceptConditions.is().displayed();
    }

    @Test
    public void labelTest() {
        assertEquals(acceptConditions.label().getText(),
            "Accept terms and conditions");
        acceptConditions.label().is().text(containsString("terms and conditions"));
        acceptConditions.label().is().text(equalToIgnoringCase("accept terms and conditions"));
    }
    @Test
    public void assertValidationTest() {
        acceptConditions.assertThat().selected();
    }

    @Test
    public void baseValidationTest() {
        baseValidation(acceptConditions);
    }
}
