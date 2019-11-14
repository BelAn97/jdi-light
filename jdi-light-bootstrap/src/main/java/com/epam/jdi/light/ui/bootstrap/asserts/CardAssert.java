package com.epam.jdi.light.ui.bootstrap.asserts;

import com.epam.jdi.light.asserts.generic.UIAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.ui.bootstrap.elements.complex.Card;

import static com.epam.jdi.light.asserts.core.SoftAssert.jdiAssert;
import static org.hamcrest.Matchers.is;

public class CardAssert extends UIAssert<CardAssert, Card> {
    @JDIAction("Assert that {0} is equal \'img\'")
    private CardAssert assertTag(String tagName) {
        jdiAssert(tagName, is("img"));
        return this;
    }

    public CardAssert imageOnTop() {
        String elementTag = element.childs().get(1).getTagName();
        assertTag(elementTag);
        return this;
    }

    public CardAssert imageBelow() {
        WebList children = element.childs();
        String elementTag = children.get(children.size()).getTagName();
        assertTag(elementTag);
        return this;
    }
}
