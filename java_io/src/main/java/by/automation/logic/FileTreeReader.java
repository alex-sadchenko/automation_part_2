package by.automation.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTreeReader {
    private List<String> stringListFromFileTree;

    public FileTreeReader(Path path) {
        try {
            this.stringListFromFileTree = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countAllDirectories() {
        return stringListFromFileTree.stream()
                .filter(string -> string.matches(".+(-->).+"))
                .count() + 1;
    }

    public long countAllFiles() {
        return stringListFromFileTree.stream()
                .filter(string -> string.matches("(\\|*\\t+)+([^>])+(\\.\\w+)?[^\\t]$"))
                .count();
    }

    public double countAverageLengthOfFileName() {
        return stringListFromFileTree.stream()
                .filter(string -> string.matches("(\\|*\\t+)+([^>])+(\\.\\w+)?[^\\t]$"))
                .map(Pattern.compile("[^\\t|]+")::matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .map(Pattern.compile("^.+(?=\\.)|^.+")::matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }

    public void printInfoFromFile() {
        long allDirectories = countAllDirectories();
        long allFiles = countAllFiles();
        System.out.println("Number of all directories - " + allDirectories);
        System.out.println("Number of all files - " + allFiles);
        System.out.printf("Average number of files in directories - %.2f\n", allFiles * 1.0/ allDirectories);
        System.out.printf("Average length of file name - %.2f\n", countAverageLengthOfFileName());
    }
}
