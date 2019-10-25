package io.github.com.sections.navbar;

import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.Link;
import com.epam.jdi.light.ui.bootstrap.elements.complex.ListGroup;

public class NavbarWithDropdown extends Section {
    @UI("ul>li") // @FindBy(css = "ul>li")
    public ListGroup navGroup;
    @UI("ul>li>a") // @FindBy(css ="ul>li>a")
    public ListGroup navItemLink;
    @JDropdown(expand = ".dropdown-toggle",
            value = ".dropdown-toggle",
            list = ".dropdown-item")
    public Dropdown dropdownMenu;
    @UI(".navbar-brand") // @FindBy(css = ".navbar-brand")
    public Link navbarLink;
}
