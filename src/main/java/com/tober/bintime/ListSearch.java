package com.tober.bintime;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

class ListSearch {

    private ArrayList<String> codeList = new ArrayList<String>() {{
    }};

    List<String> getCodeList() {
        return this.codeList;
    }

    void setCodeList(ArrayList<String> codeList) {
        this.codeList = codeList;
    }

    void addToCodeList(ArrayList<String> codeList, String value) {
        codeList.add(value);
    }

    void removeFromCodeList(ArrayList<String> codeList, String value) {
        codeList.remove(value);
    }


    List<String> getCodeListFromSite() {
        List<String> list = new ArrayList<>();
        String code;
        for (String s : getCodeList()) {
            $(byXpath("//input[@type='search']")).setValue(s);
            $(byXpath("//button[@type='submit']")).click();
            if ($(byClassName("productDetail")).$(byClassName("productCode")).isDisplayed()) {
                code = $(byClassName("productDetail")).$(byClassName("productCode")).getText().split(" ")[1];
                list.add(code);
            }
            if ($(byClassName("productNumber")).isDisplayed()) {
                code = $(byClassName("productNumber")).getText().replace("(", "").replace(")", "");
                list.add(code);
            }
        }
        return list;
    }

    boolean listsAreEquals(List<String> a, List<String> b) {
        boolean x = false;

        for (int i = 0; i < a.size(); i++) {
            x = a.get(i).equals(b.get(i));
        }
        return x;
    }
}


