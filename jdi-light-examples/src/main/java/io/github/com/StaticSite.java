package io.github.com;

import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.pageobjects.annotations.Frame;
import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import io.github.com.pages.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.jdi.light.common.CheckTypes.CONTAINS;
import static com.epam.jdi.light.common.CheckTypes.MATCH;

@JSite("https://epam.github.io/JDI/")
public class StaticSite {
	@Url("/index.html")  @Title(value = "Home", validate = CONTAINS)
	public static HomePage homePage;
	@Url("/metals-colors.html") @Title("Metal and Colors")
	public static MetalAndColorsPage metalAndColorsPage;
	@Url("/contacts.html") @Title(value = ".*tact \\w{4}", validate = MATCH)
	public static ContactFormPage contactFormPage;
	@Url("/performance.html") @Title("Performance page")
	public static PerformancePage tablePage;

	@Css("[ui=label]") public static List<WebElement> navigation;
	@Css("[ui=label]") public static WebList navigationL;
	@UI("[ui=label][*'%s']") public static WebList navigationS;

	@Css("header") public static Header header;
	@Css("footer") public static Footer footer;

	@Frame("iframe") public static HomePageFrame iframe;
	public static GithubPage githubPage;
}