package io.github.epam.html.tests.issues.issue69;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.paragraphs;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.paragraphsData;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.paragraphsList;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.template;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.templateData;
import static io.github.epam.html.tests.issues.issue69.IssuePage69.templateList;
import static io.github.epam.html.tests.issues.issue69.JDISite69.issuePage69;
import static org.testng.Assert.assertEquals;

public class IssueTest69 {
    static boolean first = true;
    @BeforeMethod
    public void before() {
        if (first) {
            initSite(JDISite69.class);
            first = false;
        }
        issuePage69.shouldBeOpened();
    }

    @Test(dataProvider = "paragraphs")
    public void issue69Tests(String paragraphName) {
        Paragraph p = paragraph(paragraphName).get(1);
        assertEquals(p.toString(), "element_0_1,element_0_2,element_0_3");
    }

    ParagraphData expectedParagraph = new ParagraphData().set(p-> {
        p.paragraph="element_0_1"; p.paragraph2="element_0_2"; p.paragraph3="element_0_3";
    });
    @Test
    public void issue69DataTests() {
        ParagraphData p = templateData.getData(1);
        assertEquals(p, expectedParagraph);
    }
    @Test
    public void issue69DataParagraphTests() {
        ParagraphData p = paragraphsData.getData(1);
        assertEquals(p, expectedParagraph);
    }


    // region DataProvider
    public static List<Paragraph> paragraph(String paragraph) {
        switch (paragraph) {
            case "template"         : return template;
            case "paragraphs"       : return paragraphs;
            case "templateList"     : return templateList;
            case "paragraphsList"   : return paragraphsList;
            case "templateData"     : return templateData;
            case "paragraphsData"   : return paragraphsData;
            default                 : return new ArrayList<>();
        }
    }

    @DataProvider(name = "paragraphs")
    public static Object[][] paragraphs() {
        return new Object[][] {
                { "template"      },
                { "paragraphs"    },
                { "templateList"  },
                { "paragraphsList"},
                { "templateData"  },
                { "paragraphsData"}
        };
    }
    // endregion
}
