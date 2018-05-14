package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTests extends ListSearch {

    //    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        open("https://www.centralpoint.nl/");
        getWebDriver().manage().window().maximize();
    }


    // TODO: П’яте завдання не повинно працювати в циклі, це не правильно.
    // todo: Є спеціальні можливості в тестових фреймворках які допомагають працювати з наборами даних.
//    @Test
    @DisplayName("With codes from test-class #Наглядно ;)")
    void oneTestCaseThatWillSearchForEachProduct0() {
        setCodeList(new ArrayList<String>() {{
            add("J153289");
            add("MQ3D2ZD/A");
            add("L36852-H2436-M101");
            add("1WZ03EA#ABH");
            add("875839-425");
            add("C5J91A#B19");
            add("FM32SD45B/10");
            add("204446-101");
            add("GV-N710D3-1GL");
            add("02G-P4-6150-KR");
        }});

        assertEquals(getCodeList(), getRawCodeListFromSite());
    }


    @Test
    void listsTest() {
        List<String> l1, l2;
        l1 = new ArrayList<String>() {{
            add("A0");
            add("A1");
            add("A3");
            add("B");
            add("C");
        }};

        l2 = new ArrayList<String>() {{
            add("C");
            add("A0");
            add("B");
            add("A1");
            add("A3");
        }};

        Collections.sort(l1);
        Collections.sort(l2);

        assertEquals(l1, l2);
    }

}
