package io.github.com.sections.card;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;

public class CardGroup extends CardWithinCardGroup {

    @UI(".card:nth-of-type(1)") public CardWithinCardGroup card1;
    @UI(".card:nth-of-type(2)") public CardWithinCardGroup card2;

}
