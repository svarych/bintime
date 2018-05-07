package com.tober.bintime;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreTests extends Store {

    @BeforeAll
    static void setUp() {
//        Configuration.browser = "phantomJs";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        open("https://www.centralpoint.nl/notebooks-laptops/");
        getWebDriver().manage().window().maximize();
    }

    @Test
    @DisplayName("Size of result set and filter data should be equals #Random filter")
    void randomFilterTest() {
        applyRandomFilter();
        assertEquals(getNumber(), sizeFromAllPages());
    }

    @Test
    @DisplayName("Size of result set and filter data should be equals #Target filter")
    void targetFilterTest() {
        open("https://www.centralpoint.nl/monitoren/");
        applyFilter("Merk", 1);
        assertEquals(getNumber(), sizeFromAllPages());
    }

    @Test
    @DisplayName("Min price on page should be equals or greater than price-filter min value #1000-1010")
    void priceFilterTest0() {
        applyPriceFilter(1000, 1010);
        assertTrue(minPriceIncl() >= getMinFilterPrice());
        assertTrue(minPriceExcl() >= getMinFilterPrice());
    }

    @Test
    @DisplayName("Min price on page should be equals or greater than price-filter min value #1000-5000")
    void priceFilterTest1() {
        open("https://www.centralpoint.nl/monitoren/");
        applyPriceFilter(1000, 5000);
        assertTrue(getMinPrice() >= getMinFilterPrice());
    }

    @AfterEach
    void clearFilters() {
        clearAllFilters();
    }
}
