package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTests extends ListSearch {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        open("https://www.centralpoint.nl/");
        getWebDriver().manage().window().maximize();
    }

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

        assertEquals(getCodeList(), getCodeListFromSite());
    }
}
