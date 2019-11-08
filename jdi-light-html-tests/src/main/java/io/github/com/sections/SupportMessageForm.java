package io.github.com.sections;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.TextArea;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import io.github.com.entities.SupportMessage;

public class SupportMessageForm extends Form<SupportMessage> {

    public TextField supportEmail;
    public TextArea supportMessage;

    public Button supportButtonSubmit;
    public Button supportButtonClear;

    public void clear(SupportMessage entity) {
        submit(entity, "clear");
    }
}
