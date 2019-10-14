package io.github.com.pages;

import com.epam.jdi.light.elements.common.Label;
import com.epam.jdi.light.elements.complex.dropdown.Dropdown;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.interfaces.complex.IsCombobox;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.Checkbox;
import com.epam.jdi.light.ui.html.elements.common.ColorPicker;
import com.epam.jdi.light.ui.html.elements.common.DateTimeSelector;
import com.epam.jdi.light.ui.html.elements.common.FileInput;
import com.epam.jdi.light.ui.html.elements.common.Image;
import com.epam.jdi.light.ui.html.elements.common.Link;
import com.epam.jdi.light.ui.html.elements.common.NumberSelector;
import com.epam.jdi.light.ui.html.elements.common.ProgressBar;
import com.epam.jdi.light.ui.html.elements.common.Range;
import com.epam.jdi.light.ui.html.elements.common.Text;
import com.epam.jdi.light.ui.html.elements.common.TextArea;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import com.epam.jdi.light.ui.html.elements.complex.Checklist;
import com.epam.jdi.light.ui.html.elements.complex.MultiSelector;
import com.epam.jdi.light.ui.html.elements.complex.RadioButtons;

public class HtmlElementsPage extends WebPage {
    //region Simple Elements
    public static Label jdiTitle;
    public static Text jdiText;
    @UI("[value*='Red Button']") public static Button redButton;
    public static Button blueButton;
    public static Button dblClickButton;
    public static Button rightClickButton;
    public static Button disabledButton;
    public static Link githubLink;
    public static TextField name;
    public static TextArea textArea;
    public static FileInput avatar;
    @UI("input[type=file][disabled]") public static FileInput disabledFileInput;
    @UI("[download]") public static Link downloadJdiLogo;
    public static Image jdiLogo;
    public static ColorPicker colorPicker;
    public static Range volume;
    public static ProgressBar progress;
    public static DateTimeSelector birthDate;
    public static DateTimeSelector monthDate;
    public static DateTimeSelector partyTime;
    public static DateTimeSelector bookingTime;
    public static DateTimeSelector autumnWeek;
    public static NumberSelector height;
    //endregion

    //region Complex Elements
    public static Dropdown dressCode;
    public static MultiSelector ages;
    public static MultiSelector multiDropdown;
    public static IsCombobox iceCream;
    public static Checkbox acceptConditions;
    @UI("[name=checks-group]")
    public static Checklist weather;
    @UI("[name=colors]")
    public static RadioButtons colors;
    //endregion

}