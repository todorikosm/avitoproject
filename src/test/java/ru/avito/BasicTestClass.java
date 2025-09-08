package ru.avito;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;

import java.io.IOException;

public class BasicTestClass {
//    protected org.slf4j.Logger logger = getLogger()

    @BeforeEach
    public void setupBrowser() {
        String driverName;
        driverName = System.getProperty("TestWebDriver");
//        logger.info("Пользователь получает информацию о браузере: " + driverName);

        if (driverName == null) {
            getDriver();
        }
    }

    public void  getDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/yandexdriver.exe");
        Configuration.pageLoadStrategy = "none";
        Configuration.browserSize = "1920x1080";
        Configuration.webdriverLogsEnabled = true;
        Configuration.timeout = 120000;
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
