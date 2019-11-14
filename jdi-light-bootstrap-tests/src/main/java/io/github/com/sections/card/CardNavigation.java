package io.github.com.sections.card;

import com.epam.jdi.light.elements.complex.Menu;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.Button;
import com.epam.jdi.light.ui.bootstrap.elements.common.Link;
import com.epam.jdi.light.ui.bootstrap.elements.common.Text;
import com.epam.jdi.light.ui.bootstrap.elements.complex.Card;

public class CardNavigation extends Card {

    @UI(".nav")
    public Menu nav;

    @UI("#activeLink")
    public Link activeLink;
    @UI("#jdiLink")
    public Link jdiLink;
    @UI("#disabledLink")
    public Link disabledLink;

    @UI("h5")
    public Text title;
    @UI(".card-text")
    public Text subtitle;
    @UI("button")
    public Button button;
}
