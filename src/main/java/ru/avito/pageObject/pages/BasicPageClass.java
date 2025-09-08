package ru.avito.pageObject.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BasicPageClass {

    public String getServer() {
        String res = "";
        try {
            res = (Files.readString(Path.of("src/test/resources/config/Serv")));
        } catch (IOException e) {
//            Assert.assertTrue(false);
        }
        return res;
    }
}
