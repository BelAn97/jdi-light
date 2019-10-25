package io.github.com.sections.form;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.bootstrap.elements.common.Button;
import com.epam.jdi.light.ui.bootstrap.elements.common.Checkbox;
import com.epam.jdi.light.ui.bootstrap.elements.common.RadioButton;
import com.epam.jdi.light.ui.bootstrap.elements.common.TextField;
import io.github.com.entities.FormContacts;

public class FormHorizontal extends Form<FormContacts> {

    @UI("#inputEmail3")
    public TextField email;
    @UI("#inputPassword3")
    public TextField password;
    @UI("input[name='gridRadios']")
    public RadioButton radio;
    @UI("#gridCheck1")
    public Checkbox accept;
    @UI("button")
    public Button submit;

}
