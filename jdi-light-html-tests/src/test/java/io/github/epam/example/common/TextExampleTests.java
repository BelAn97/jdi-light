package io.github.epam.example.common;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.html5Page;
import static io.github.com.pages.HtmlElementsPage.jdiText;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedIn;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Test examples for Text with JDI Light asserts
 *
 */
public class TextExampleTests implements TestsInit {

    private static final String TEXT = "Powerful Framework for UI Tests Automation. Suitable for any UI project: Web(Html5, Angular, React...), Mobile(Android IOs), Desktop(Win app) etc.";
    private static final String PART_OF_TEXT = "Powerful Framework for UI Tests Automation.";
    private static final String NOT_EXPECTED_TEXT = "GITHUB";

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        html5Page.shouldBeOpened();
    }

    @Test
    public void textTest() {
        jdiText.is()
                .enabled()
                .and()
                .displayed()
                .core()
                .css("font-size", is("14px"));
    }

    @Test
    public void textPositiveTest(){
        jdiText.is().text(equalTo(TEXT));
        jdiText.is().text(TEXT);
    }

    @Test
    public void textContainsTest() {
        jdiText.is().text(containsString(PART_OF_TEXT));
    }

    @Test
    public void textDoesNotContainWordTest() {
        jdiText.is().text(not(NOT_EXPECTED_TEXT));
    }
}
