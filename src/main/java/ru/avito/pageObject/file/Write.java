package ru.avito.pageObject.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Write {

    public void writeUrlIntoFile(List<String> list, String fileName) throws IOException {
        try {
            File file = new File(fileName + ".txt");
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла");
            e.printStackTrace();
        }

        FileWriter writer = new FileWriter(fileName + ".txt");

        for (int i = 0; i < list.size(); i++) {
            String content = list.get(i);
            writer.write(content);
            writer.close();
        }
    }
}
