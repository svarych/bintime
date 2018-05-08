package com.tober.bintime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ALL OK
 */

class RestTests extends Rest {

    RestTests() throws IOException {
    }

    @Test
    @DisplayName("Latvia should have border with Estonia")
    void latviaShouldHaveBorderWithEstonia() throws IOException {
        sendGetRequest();
        assertEquals(200, getResponseCode());
        assertTrue(getCountry("Latvia").get("borders").toString()
                .contains(getCountry("Estonia").get("alpha3Code").toString()));
    }

    @Test
    @DisplayName("Check that Ukraine has area more than 500000.0")
    void ukraineAreaShouldBeMoreThan() throws IOException {
        sendGetRequest();
        assertEquals(200, getResponseCode());
        assertTrue(parseDouble(getCountry("Ukraine").get("area").toString()) > 500000.0);
    }

    @Test
    @DisplayName("Output values: Name, Capital, Region, Population, Borders.")
    void getOutputValues() throws IOException {
        sendGetRequest();
        assertEquals(200, getResponseCode());
        assertEquals(getCountry("Ukraine").get("name").toString(), "\"Ukraine\"");
        assertEquals(getCountry("Ukraine").get("capital").toString(), "\"Kiev\"");
        assertEquals(getCountry("Ukraine").get("region").toString(), "\"Europe\"");
        assertEquals(getCountry("Ukraine").get("population").toString(), "42836922");
        assertEquals(getCountry("Ukraine").get("borders").toString(), "[\"BLR\",\"HUN\",\"MDA\",\"POL\",\"ROU\",\"RUS\",\"SVK\"]");
    }
}
