package com.tober.bintime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RestTests extends Rest {

    RestTests() throws IOException {
    }

    @Test
    @DisplayName("Latvia should have border with Estonia")
    void latviaShouldHaveBorderWithEstonia() throws IOException {
        sendGetRequest();
        assertTrue(getCountry("Latvia").get("borders").toString()
                .contains(getCountry("Estonia").get("alpha3Code").toString()));
    }

    @Test
    @DisplayName("Check that Ukraine has area more than 500000.0")
    void ukraineAreaShouldBeMoreThan() throws IOException {
        sendGetRequest();
        assertTrue(Double.parseDouble(getCountry("Ukraine").get("area").toString()) > 500000.0);
    }

    @Test
    @DisplayName("Output values: Name, Capital, Region, Population, Borders.")
    void getOutputValues() throws IOException {
        sendGetRequest();
        System.out.println(getCountry("Ukraine").get("name"));
        System.out.println(getCountry("Ukraine").get("capital"));
        System.out.println(getCountry("Ukraine").get("region"));
        System.out.println(getCountry("Ukraine").get("population"));
        System.out.println(getCountry("Ukraine").get("borders"));
    }


}
