package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTests extends ListSearch {

//    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.fastSetValue = true;
        open("https://www.centralpoint.nl/");
        getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Assert that all codes from test-class contains in result set.")
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

        assertTrue(getRawCodeListFromSite().containsAll(getCodeList()));
    }

    @Test
    @DisplayName("Assert that all codes from data file contains in result set.")
    void oneTestCaseThatWillSearchForEachProduct1() {
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

        // Перевіряємо, чи всі елементи початкового списку присутні в результаті видачі
        assertTrue(getRawCodeListFromSite().containsAll(getCodeList()));
    }

    @Test
    @DisplayName("Assert that code list from data file equals with result set.")
    void oneTestCaseThatWillSearchForEachProduct2() {
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

        // Перевіряємо, чи списки однакові
        assertEquals(getCodeList(), getRawCodeListFromSite());
    }

    @Test
    void file() throws IOException {
        setCodeList("./src/main/resources/codes.txt");
        System.out.println(getCodeList());
    }
}
