package ru.avito.pageObject.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

class ResultAfterSearchTest {
    private SelenideElement allAnnouncements =
            $x("//h1[contains(text(), ': объявления')]");

}