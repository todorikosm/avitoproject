package ru.avito.pageObject.pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.avito.BasicTestClass;
import ru.avito.pageObject.file.Write;

import java.io.IOException;

class StarterPageTest extends BasicTestClass {
    Write fileWriter = new Write();

    @Test
    @DisplayName("")
    public void firstUserSearchIntoVideo() {
        String searchValue = "xiaomi  mi  tv  stick  4k";

        StarterPage starterPage = new StarterPage();
        starterPage.searchFor(searchValue);
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