package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
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
    @DisplayName("one test-case that will search for each product #Enter")
    void oneTestCaseThatWillSearchForEachProductEnter() {
        String code = null;
        for (String s : getList()) {
            $(byXpath("//input[@type='search']")).setValue(s).pressEnter();

            if ($(byClassName("productDetail")).$(byClassName("productCode")).isDisplayed()) {
                code = $(byClassName("productDetail")).$(byClassName("productCode")).getText().split(" ")[1];
            }
            if ($(byClassName("productNumber")).isDisplayed()) {
                code = $(byClassName("productNumber")).getText().replace("(", "").replace(")", "");
            }
            assertEquals(code, s);
        }
    }

    @Test
    @DisplayName("one test-case that will search for each product #Search button")
    void oneTestCaseThatWillSearchForEachProduct() {
        String code = null;
        for (String s : getList()) {
            $(byXpath("//input[@type='search']")).setValue(s);
            $(byXpath("//button[@type='submit']")).click();
            if ($(byClassName("productDetail")).$(byClassName("productCode")).isDisplayed()) {
                code = $(byClassName("productDetail")).$(byClassName("productCode")).getText().split(" ")[1];
            }
            if ($(byClassName("productNumber")).isDisplayed()) {
                code = $(byClassName("productNumber")).getText().replace("(", "").replace(")", "");
            }
            assertEquals(code, s);
        }
    }
}
