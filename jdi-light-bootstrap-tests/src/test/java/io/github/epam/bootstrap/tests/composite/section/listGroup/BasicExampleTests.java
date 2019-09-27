package io.github.epam.bootstrap.tests.composite.section.listGroup;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.listGroupBasicExample;
import static io.github.epam.states.States.shouldBeLoggedIn;
import static org.hamcrest.Matchers.is;

/**
 * Created by Dmitrii Pavlov on 25.09.2019
 * Email: delnote@gmail.com; Skype: Dmitrii Pavlov
 */

public class BasicExampleTests extends TestsInit {

    private String text1 = "Cras justo odio";
    private String text2 = "Dapibus ac facilisis in";
    private String text3 = "Morbi leo risus";
    private String text4 = "Porta ac consectetur ac";
    private String text5 = "Vestibulum at eros";
    private String listClass = "list-group-item";

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
    }

    @DataProvider
    public Object[][] listData() {
        return new Object[][]{
                {1, text1}, {2, text2}, {3, text3}, {4, text4}, {5, text5}
        };
    }

    @Test(dataProvider = "listData")
    public void listGroupTests(int num, String text) {
        listGroupBasicExample.listGroup.is()
                .size(5);
        listGroupBasicExample.listGroup.get(num).is()
                .text(text)
                .css("font-size", is("14px"));
    }

    @Test(dataProvider = "listData")
    public void listGroupIsValidationTests(int num, String text) {
        listGroupBasicExample.is()
                .displayed()
                .enabled()
                .core()
                .hasClass("list-group");
        listGroupBasicExample.listGroup.get(num).is()
                .displayed()
                .enabled()
                .core()
                .hasClass(listClass);
    }
}
