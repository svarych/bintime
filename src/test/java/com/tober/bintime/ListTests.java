package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTests extends ListSearch {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        open("https://www.centralpoint.nl/");
        getWebDriver().manage().window().maximize();
    }


    // TODO: П’яте завдання не повинно працювати в циклі, це не правильно.
    // todo: Є спеціальні можливості в тестових фреймворках які допомагають працювати з наборами даних.
    @Test
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

//        assertArrayEquals(getCodeList().toArray(), getRawCodeListFromSite().toArray());
        System.out.println(getCodeList());
        System.out.println(getRawCodeListFromSite());
    }


}
