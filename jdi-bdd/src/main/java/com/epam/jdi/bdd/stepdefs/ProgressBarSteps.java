package com.epam.jdi.bdd.stepdefs;

import com.epam.jdi.light.ui.html.elements.common.ProgressBar;
import cucumber.api.java.en.Then;

import static com.epam.jdi.light.elements.init.entities.collection.EntitiesCollection.getUI;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Created by Roman Iovlev on 26.09.2019
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public class ProgressBarSteps {
    public static ProgressBar progressBar(String name) { return getUI(name, ProgressBar.class); }

    @Then("^the \"([^\"]*)\" progress volume greater or equal to (\\d+)$")
    public void volumeGreaterOrEqualTo(String name, int value) {
        progressBar(name).is().volume(greaterThanOrEqualTo(value));
    }
    @Then("^the \"([^\"]*)\" progress volume less or equal to (\\d+)$")
    public void volumeLessOrEqualTo(String name, int value) {
        progressBar(name).is().volume(lessThanOrEqualTo(value));
    }
}
