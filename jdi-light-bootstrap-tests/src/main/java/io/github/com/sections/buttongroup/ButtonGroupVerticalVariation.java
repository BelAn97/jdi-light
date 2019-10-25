package io.github.com.sections.buttongroup;

import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.Button;

public class ButtonGroupVerticalVariation extends Section {

    @UI("//button[text()='Button one']") public Button buttonOne;
    @UI("//button[text()='Button two']") public Button buttonTwo;
    @JDropdown(expand = ".btn-group",
            value = ".dropdown-menu",
            list = ".dropdown-item")
    public Dropdown dropdownMenu;

}
