package com.tober.bintime;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Collections.min;

public class Store {

    private int numberOfGoods;

    public int getNumber() {
        return numberOfGoods;
    }

    private double minFilterPrice;

    double getMinFilterPrice() {
        return minFilterPrice;
    }

    private void setMinFilterPrice(double minFilterPrice) {
        this.minFilterPrice = minFilterPrice;
    }

    private void setNumberOfGoods(@Nonnull SelenideElement e) {
        numberOfGoods = Integer.parseInt(e.find(byClassName("number"))
                .getText()
                .replace("(", "")
                .replace(")", "")
        );
    }

    void applyPriceFilter(int min, int max) {
        setMinFilterPrice((double) min);
        SelenideElement filter = $(byXpath("(//div[@class='dropdown']/../div[@class='filterHead'][.='Prijs'])"));
        filter.shouldBe(visible).click();
        setPrice(min, max);
        submit();
    }

    double getMinPrice(PriceType type) {
        $(byClassName("icon-CP-Grid")).shouldBe(visible).click();
        List<String> excl = new ArrayList<>();
        List<String> incl = new ArrayList<>();
        ElementsCollection e;
        ElementsCollection i;

        SelenideElement nextButton = $(byXpath("(//a[.='Volgende '])[1]"));
        if (nextButton.isDisplayed()) {
            while (nextButton.isDisplayed()) {

                e = $$(byXpath("//div[contains(@class,'priceExcl')]"));
                i = $$(byXpath("//div[contains(@class,'priceIncl')]"));

                for (SelenideElement price : e) {
                    excl.add(price.getText());
                }
                for (SelenideElement price : i) {
                    incl.add(price.getText());
                }
                nextButton.shouldBe(visible).click();
            }
        } else {
            e = $$(byXpath("//div[contains(@class,'priceExcl')]"));
            i = $$(byXpath("//div[contains(@class,'priceIncl')]"));

            for (SelenideElement price : e) {
                excl.add(price.getText());
            }
            for (SelenideElement price : i) {
                incl.add(price.getText());
            }
        }

        System.out.println(excl);
        System.out.println(incl);

        if (type == PriceType.EXCL) {
            System.out.println(minimum(excl));
            return minimum(excl);
        }
        if (type == PriceType.INCL) {
            System.out.println(minimum(incl));
            return minimum(incl);
        } else {
            System.out.println("NO PRICE LIST");
            return 0.0;
        }
    }

    enum PriceType {
        EXCL, INCL
    }

    double minPriceExcl() {
        ElementsCollection prices = $$(byXpath("//div[contains(@class,'priceExcl')]"));
        return minimum(prices);
    }

    double minPriceIncl() {
        ElementsCollection prices = $$(byXpath("//div[contains(@class,'priceIncl')]"));
        return minimum(prices);
    }

    private double minimum(@Nonnull ElementsCollection collection) {
        List<Double> priceList = new ArrayList<>();
        for (SelenideElement price : collection) {
            String txt = price.getText().split(" ")[0]
                    .replace(",-", ".0")
                    .replace(",", ".");
            priceList.add(Double.parseDouble(txt));
        }
        int index = priceList.indexOf(min(priceList));
        return priceList.get(index);
    }

    private double minimum(@Nonnull List<String> collection) {
        List<Double> priceList = new ArrayList<>();
        for (String price : collection) {
            String txt = price.split(" ")[0]
                    .replace(",-", ".0")
                    .replace(",", ".");
            priceList.add(Double.parseDouble(txt));
        }
        int index = collection.indexOf(min(collection));
        return priceList.get(index);
    }

    void applyFilter(String name, int index) {
        SelenideElement filter = $(byXpath("(//div[@class='dropdown']/../div[@class='filterHead'][.='" + name + "'])"));
        filter.shouldBe(visible).click();
        checkBox(index);
        submit();
    }

    void applyRandomFilter() {
        ElementsCollection filters = $$(byXpath("(//div[@class='dropdown']/../div[@class='filterHead'])"));
        List<SelenideElement> elements = new ArrayList<>(filters);

        for (SelenideElement filter : filters) {
            if (filter.getText().equals("Prijs") || filter.getText().equals("Sortering")) {
                elements.remove(filter);
            }
        }

        SelenideElement randomFilter = elements.get((int) (Math.random() * elements.size()));
        System.out.println("filter: " + randomFilter.getText());
        randomFilter.shouldBe(visible).click();

        checkRandomBox();
        submit();
    }

    private void setPrice(int min, int max) {
        $(byXpath("//input[@id='priceRangeLow']")).setValue(String.valueOf(min));
        $(byXpath("//input[@id='priceRangehigh']")).setValue(String.valueOf(max));
    }

    private void checkBox(int i) {
        SelenideElement box = $(byXpath("(//div[contains(@class, 'filter active')]//ul/li/label)[" + i + "]"));
        box.scrollIntoView(false).hover().shouldBe(visible);

        //Remember count of goods
        setNumberOfGoods(box);

        //Checking box
        box.click();
    }

    private void checkRandomBox() {
        ElementsCollection boxes = $$(byXpath("(//div[contains(@class, 'filter active')]//ul/li/label)"));
        int random = (int) (Math.random() * boxes.size());

        SelenideElement box = boxes.get(random);
        box.scrollIntoView(false).hover().shouldBe(visible);
        setNumberOfGoods(box);
        box.click();

        System.out.println("box text: " + box.getText());
    }

    private void submit() {
        if ($(byXpath("//button[.='Sluiten']")).isDisplayed()) {
            $(byXpath("//button[.='Sluiten']")).click();
        } else if ($(byXpath("//button[.='Zoeken']")).isDisplayed()) {
            $(byXpath("//button[.='Zoeken']")).click();
        }
    }

    private int getDomSize() {
        if (numberOfGoods > 1) {
            $(".icon-CP-Grid").click();
            ElementsCollection cards = $$(byXpath("//div[contains(@class, 'card portrait')]"));
            cards.shouldHave(CollectionCondition.sizeGreaterThan(0));
            return cards.size();
        } else return 1;
    }


    int sizeFromAllPages() {
        int i = getDomSize();
        if (numberOfGoods > 72) {
            SelenideElement nextButton = $(byXpath("(//a[.='Volgende '])[1]"));
            while (nextButton.isDisplayed()) {
                try {
                    $(nextButton).shouldBe(visible).click();
                } catch (Throwable th) {
                    System.out.println("No more pages");
                }
                i += getDomSize();
            }
        }
        return i;
    }

    void clearAllFilters() {
        String filters = "//div[@class='filterSummary']//li";
        for (SelenideElement filter : $$(byXpath(filters))) {
            while (filter.isDisplayed()) {
                System.out.println("Closing filter: " + filter.getText());
                filter.find(byClassName("close")).click();
            }
        }
    }

}
