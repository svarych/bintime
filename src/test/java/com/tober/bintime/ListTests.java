package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListTests extends ListSearch {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.fastSetValue = true;
        open("https://www.centralpoint.nl/");
        getWebDriver().manage().window().maximize();
    }

    // Using data provider #TOO SLOW!
    @ParameterizedTest
    @ValueSource(strings =
            {
                    "J153289",
                    "MQ3D2ZD/A",
                    "L36852-H2436-M101",
                    "1WZ03EA#ABH",
                    "875839-425",
                    "C5J91A#B19",
                    "FM32SD45B/10",
                    "204446-101",
                    "GV-N710D3-1GL",
                    "02G-P4-6150-KR"
            })
    @DisplayName("using data provider")
    void oneTestCaseThatWillSearchForEachProduct0(String code) throws Exception {
        setCode(code);
        assertEquals(code, getCodeFromProductPage());
    }

    // Using List<> # should be GREEN - assert CONTAINS
    @Test
    @DisplayName("Assert that all codes from test-class CONTAINS in result set.")
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

        assertTrue(getRawCodeListFromSite().containsAll(getCodeList()));
    }

    // Using data file # should be RED - assert EQUALS
    @Test
    @DisplayName("Assert that all codes from data file CONTAINS in result set.")
    void oneTestCaseThatWillSearchForEachProduct2() throws IOException {
        setCodeList("./src/main/resources/codes.txt");
        assertTrue(getRawCodeListFromSite().containsAll(getCodeList()));
    }

    // Using data file # should be GREEN - assert CONTAINS
    @Test
    @DisplayName("Assert that code list from data file EQUALS with result set.")
    void oneTestCaseThatWillSearchForEachProduct3() throws IOException {
        setCodeList("./src/main/resources/codes.txt");
        assertEquals(getCodeList(), getRawCodeListFromSite());
    }

}
