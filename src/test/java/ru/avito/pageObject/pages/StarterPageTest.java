package ru.avito.pageObject.pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import ru.avito.BasicTestClass;
import ru.avito.pageObject.file.Write;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

class StarterPageTest extends BasicTestClass {
    Write fileWriter = new Write();

    private static Logger logger = getLogger(StarterPageTest.class);

    @Test
    @DisplayName("")
    public void firstUserSearchIntoVideo() {
        String searchValue = "xiaomi  mi  tv  stick  4k";

        StarterPage starterPage = new StarterPage();
        logger.info("Пользователь находится на главной странице.");
        starterPage.searchFor(searchValue);
        logger.info("Пользователь выполняет поиск по значению: {}", searchValue);
        var resultAfterSearch = starterPage.selectIntoDropdownMenu("← Видео, DVD и Blu-ray плееры");
//        resultAfterSearch.selectPrice(1, 1500);
        resultAfterSearch.sortBy("По дате");
        resultAfterSearch.showItems();
        try {
            fileWriter.writeUrlIntoFile(resultAfterSearch.checkItems(), searchValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}