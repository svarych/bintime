package com.tober.bintime;

import com.codeborne.selenide.SelenideElement;

import java.util.*;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class ListSearch {

    private SelenideElement searchField = $(byXpath("//input[@type='search']"));
    private SelenideElement submitButton = $(byXpath("//button[@type='submit']"));

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


    // TODO: П’яте завдання не повинно працювати в циклі, це не правильно.
    List<String> getRawCodeListFromSite() {
        List<String> list;
        searchField.setValue(getCodeList().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        );
        submitButton.click();

        list = $$(byClassName("productNumber")).texts();

//        return (getCodeListFromSite(list)).sort(Comparator.comparing(getCodeList().size()));
    }

    private List<String> getCodeListFromSite(List<String> list) {
        List<String> l = new LinkedList<>();
        for (String valueFromSite : list) {
            for (String valueFromInput : getCodeList()){
                if (valueFromSite.contains(valueFromInput)){
                    if (!l.contains(valueFromInput)){
                        l.add(valueFromInput);
                    }
                }
            }
        }
        return l;
    }

    private String unique(String s1, String s2){
        if (s1.equals(s2)){
            return s1;
        }
        return null;
    }

    boolean listsAreEquals(List<String> first, List<String> second) {
        boolean x = false;
        for (String item : first) {
            x = item.equals(second.get(first.indexOf(item)));
        }

        if (!x) {
            System.err.println("Lists are different!");
            System.err.println("one: " + first);
            System.err.println("two: " + second);
        }

        return x;
    }
}
