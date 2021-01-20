package by.automation;

import by.automation.logic.FileTreeReader;
import by.automation.logic.PathTreeWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Реализовать программу которая будет получать в качестве аргумента командной строки путь к директории,
// например "D:/movies". Записать в текстовый файл структуру папок и файлов в виде,
// похожем на выполнение программы tree /F.
//
// Если в качестве параметра в программу передается не путь к директории,
// а путь к txt файлу по образцу выше - прочитать файл и вывести в консоль следующие данные:
// - Количество папок
// - Количество файлов
// - Среднее количество файлов в папке
// - Среднюю длинну названия файла

public class Runner {
    public static void main(String[] args) {

        Path pathFromCommandLine = Paths.get(args[0]);

        if (Files.isDirectory(pathFromCommandLine)) {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src", "main", "resources", "tree.txt"))) {
                Files.walkFileTree(pathFromCommandLine, new PathTreeWriter(bufferedWriter));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Files.isRegularFile(pathFromCommandLine)) {
            new FileTreeReader(pathFromCommandLine).printInfoFromFile();
        } else {
            System.out.println("Not file nor directory");
        }
    }
}
