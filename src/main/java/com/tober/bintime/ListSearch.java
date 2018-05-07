package com.tober.bintime;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ListSearch {

    private ArrayList<String> codeList = new ArrayList<String>() {{
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
    }};

    public List<String> getCodeList() {
        return this.codeList;
    }

    public void setCodeList(ArrayList<String> codeList) {
        this.codeList = codeList;
    }

    public void addToCodeList(ArrayList<String> codeList, String value) {
        codeList.add(value);
    }

    public void removeFromCodeList(ArrayList<String> codeList, String value) {
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


