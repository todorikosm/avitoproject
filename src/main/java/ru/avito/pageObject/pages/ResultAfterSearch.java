package ru.avito.pageObject.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ResultAfterSearch {

    public static List<String> urlArr = new ArrayList<>();

    private String currentUrl;
    private SelenideElement sort =
            $x("//span[contains(text(), 'Сортировка')]");
    private SelenideElement dropdown =
            $x("//div[contains(@data-marker, 'sort/dropdown')]");
    private SelenideElement priceFromInput =
            $x("//input[contains(@marker, 'price-from')]");
    private SelenideElement priceToInput =
            $x("//input[contains(@marker, 'price-to')]");
    private ElementsCollection items =
            $$x("//div[@data-marker= 'item']" +
                    "[descendant::p[contains(text(), ' час назад') " +
                    "or contains(text(), 'часа назад') " +
                    "or contains(text(), 'часов назад')" +
                    "or contains(text(), 'Вчера')]]");

    public void sortBy(String label) {
        sort.click();
        Selenide.sleep(3000);
        $x("//button[descendant::div[contains(text(), '" + label + "')]]")
                .shouldBe(clickable).click();
    }

    public void selectPrice(int priceFrom, int priceTo) {
        priceFromInput.setValue(String.valueOf(priceFrom));
        Selenide.sleep(1000);
        priceToInput.click();
        Selenide.sleep(1000);
        priceToInput.setValue(String.valueOf(priceTo));
    }

    public void showItems() {
        $x("//button[descendant::span[contains(text(), 'Показать')]]")
                .shouldBe(clickable).click();
        Selenide.sleep(1500);
    }

    public List<String> checkItems() {
        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).click();
                Selenide.switchTo().window(1);
                $x("//h2[contains(text(), 'Характеристики')]").shouldBe(visible);
                $x("//span[contains(text(), 'Товар зарезервирован')]").shouldNotBe(visible);
                currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
                urlArr.add(currentUrl);
                Selenide.closeWindow();
                Selenide.switchTo().window(0);
            }
        }
        return urlArr;
    }

}
