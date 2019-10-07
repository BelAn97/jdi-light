package io.github.epam.bootstrap.tests.complex;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.bsPage;
import static io.github.com.pages.BootstrapPage.*;
import static io.github.epam.states.States.shouldBeLoggedIn;

/**
 * Created by Zaur Bibilov on 19.09.2019
 * Email: zaur_bibilov@epam.com;
 * Skype: dasilveria
 */

public class CollapseTests extends TestsInit {

    // List texts to check
    String cardOneText = "Anim pariatur cliche reprehenderit.";
    String cardTwoText = "Craft beer labore wes anderson cred nesciunt sapiente ea proident.";
    String cardThreeText = "High life accusamus terry richardson ad squid. Nihil anim.";
    String groupOneText = "You probably haven't heard of them accusamus labore sustainable VHS.";
    String groupTwoText = "Nihil anim keffiyeh helvetica, craft beer labore.";
    String groupThreeText = "3 wolf moon officia aute, non cupidatat skateboard dolor brunch.";

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        bsPage.shouldBeOpened();
    }

    @Test(priority = 1)
    public void collapseLinkTest() {
        collapseLink.highlight();
        collapseLink.expand();

        collapseLink.is().expanded();
        collapseLink.value().is().text(cardOneText);

        collapseLink.collapse();
        collapseLink.is().collapsed();
    }

    @Test(priority = 2)
    public void collapseButtonTest() {
        collapseDataTarget.highlight();
        collapseDataTarget.expand();

        collapseDataTarget.is().expanded();
        collapseDataTarget.value().is().text(cardOneText);

        collapseDataTarget.collapse();
        collapseDataTarget.is().collapsed();
    }

    @Test(priority = 3)
    public void collapseToggleOneTest() {
        collapseToggleOne.highlight();
        collapseToggleOne.expand();

        collapseToggleOne.is().expanded();
        collapseToggleOne.value().is().text(cardTwoText);

        collapseToggleOne.collapse();
        collapseToggleOne.is().collapsed();
    }

    @Test(priority = 4)
    public void collapseToggleTwoTest() {
        collapseToggleTwo.highlight();
        collapseToggleTwo.expand();

        collapseToggleTwo.is().expanded();
        collapseToggleTwo.value().is().text(cardThreeText);

        collapseToggleTwo.collapse();
        collapseToggleTwo.is().collapsed();
    }

    @Test(priority = 5)
    public void collapseToggleBothTest() {
        collapseToggleBoth.highlight();
        collapseToggleBoth.expand();

        collapseToggleBoth.is().expanded();
        collapseToggleBoth.value().is().text(cardTwoText);
        collapseToggleTwo.value().is().text(cardThreeText);

        collapseToggleBoth.collapse();
        collapseToggleBoth.is().collapsed();
    }

    @Test(priority = 6)
    public void collapseGroupOneTest() {
        collapseGroupOne.highlight();
        collapseGroupOne.expand();

        collapseGroupOne.is().expanded();
        collapseGroupOne.value().is().text(groupOneText);

        collapseGroupOne.collapse();
        collapseGroupOne.is().collapsed();
    }

    @Test(priority = 7)
    public void collapseGroupTwoTest() {
        collapseGroupTwo.highlight();
        collapseGroupTwo.expand();

        collapseGroupTwo.is().expanded();
        collapseGroupTwo.value().is().text(groupTwoText);

        collapseGroupTwo.collapse();
        collapseGroupTwo.is().collapsed();
    }

    @Test(priority = 8)
    public void collapseGroupThreeTest() {
        collapseGroupThree.highlight();
        collapseGroupThree.expand();

        collapseGroupThree.is().expanded();
        collapseGroupThree.value().is().text(groupThreeText);

        collapseGroupThree.collapse();
        collapseGroupThree.is().collapsed();
    }

}