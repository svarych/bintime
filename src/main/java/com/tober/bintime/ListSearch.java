package com.tober.bintime;

import com.codeborne.selenide.SelenideElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

class ListSearch {

    private SelenideElement searchField = $(byXpath("//input[@type='search']"));
    private SelenideElement submitButton = $(byXpath("//button[@type='submit']"));

    private ArrayList<String> codeList = new ArrayList<String>() {{
    }};

    List<String> getCodeList() {
        Collections.sort(this.codeList);
        return this.codeList;
    }

    void setCodeList(ArrayList<String> codeList) {
        this.codeList = codeList;
    }

    void setCodeList(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            codeList.add(line);
        }

    }

    List<String> getRawCodeListFromSite() {
        List<String> list;
        searchField.clear();
        searchField.setValue(getCodeList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        );
        submitButton.click();

        list = $$(byClassName("productNumber")).texts();
        return getResultSet(list);
    }

    private List<String> getResultSet(List<String> list) {
        List<String> cleanSet = new ArrayList<>();
        for (String element : list) {
            cleanSet.add(element.replace("(", "").replace(")", ""));
        }
        Collections.sort(cleanSet);
        return cleanSet;
    }

    void setCode(String code) {
        checkForPageIsLoaded();

        SelenideElement searchField = $(byXpath("//input[@type='search']"));
        searchField.setValue(code).pressEnter().clear();

    }

    String getCodeFromProductPage() throws Exception {
        SelenideElement productCode = $(byXpath("//span[@class='productCode']"));
        String returnCode = null;

        checkForPageIsLoaded();
        if (productCode.isDisplayed()) {
            returnCode = $(byXpath("//span[@class='productCode']")).getText().split(":")[1].split("\\|")[0].trim();
        }
        else if ($$(byXpath("//div[contains(@class,'card')]")).size() > 1) {
            throw new Exception("It is more than one product on page!");
        }
        return returnCode;
    }

    private void checkForPageIsLoaded() {
        if ($(byXpath("//div[@class='error-code']")).isDisplayed()) {
            if ($(byXpath("//div[@class='error-code']")).getText().equals("HTTP ERROR 500")) {
                getWebDriver().navigate().back();
            }
        }
    }
}
