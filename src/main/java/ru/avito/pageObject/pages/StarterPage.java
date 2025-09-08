package ru.avito.pageObject.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class StarterPage extends BasicPageClass {

    private String url = "";
    private SelenideElement searchInput =
            $x("//input[contains(@marker, 'search-form/suggest')]");
    private SelenideElement searchButton =
            $x("//button[contains(@data-marker, 'search-form/submit-button')]");
    private SelenideElement modalBtn =
            $x("//button[descendant::span[contains(text(), 'Хорошо')]]" +
                    "[contains(@class, 'module-root')]");
    private SelenideElement dropdownMenu =
            $x("//div[contains(@class, 'module-dropdownScrollWrapper')]");

    public StarterPage() {
        url = new BasicPageClass().getServer();
        open(url);
    }

    @Step("Вводится значение '{text}' в окно поиска")
    public void searchFor(String text) {
        SelenideElement searchInputWithValue =
                $x("//input[@value = '" + text + "']");
        while (!searchInputWithValue.isDisplayed()) {
            searchInput.clear();
            searchInput.setValue(" ");
            searchInput.setValue(text);
        }
        Selenide.sleep(3000);
    }

    @Step("Выполняется выбор в выпадающем меню поиска")
    public ResultAfterSearch selectIntoDropdownMenu(String category) {
        dropdownMenu.should(exist);
        $x("//button[contains(@role, 'checkbox')]" +
                "[descendant::span[text()='" + category + "']]").click();
        searchButton.click();
        $x("//h1[contains(text(), 'объявления для')]").shouldBe(visible);
        return new ResultAfterSearch();
    }

    public void checkModalWindowOnMainPage() {
        if (modalBtn.isDisplayed()) {
            modalBtn.click();
        }
    }
}
