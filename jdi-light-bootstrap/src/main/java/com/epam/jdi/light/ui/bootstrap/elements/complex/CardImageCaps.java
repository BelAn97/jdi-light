package com.epam.jdi.light.ui.bootstrap.elements.complex;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.asserts.CardImageCapsAssert;
import com.epam.jdi.light.ui.bootstrap.elements.common.Image;
import com.epam.jdi.light.ui.bootstrap.elements.common.Text;

public class CardImageCaps extends Card {
    @UI(".card-img-top")
    public Image image;
    @UI(".card-body")
    public Text text;

    public CardImageCapsAssert is() { return new CardImageCapsAssert().set(this);}
}
