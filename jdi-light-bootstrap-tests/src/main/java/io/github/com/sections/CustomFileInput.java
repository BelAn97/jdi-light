package io.github.com.sections;

import com.epam.jdi.light.elements.common.Label;
import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.FileInput;

public class CustomFileInput extends Section {

    @UI(".input-group-text") public Label label;
    @UI(".custom-file-input") public FileInput inputField;
    @UI(".custom-file-label") public Label inputText;
}
