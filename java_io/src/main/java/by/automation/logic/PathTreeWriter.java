package by.automation.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

public class PathTreeWriter extends SimpleFileVisitor<Path> {
    private int depth;
    private final BufferedWriter bufferedWriter;

    public PathTreeWriter(BufferedWriter bufferedWriter) {
        this.depth = 0;
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (depth == 0) {
            bufferedWriter.write(dir.toString().toUpperCase());
        } else {
            bufferedWriter.write(markUp(depth, dir.getParent()).replaceAll("\t$", "-->") + dir.getFileName());
        }
        bufferedWriter.newLine();
        depth++;
        String markUp = markUp(depth, dir);

        Files.list(dir)
                .filter(Files::isRegularFile)
                .peek(file -> write(markUp + file.getFileName()))
                .reduce((file1, file2) -> file2)
                .ifPresent(file -> write(markUp));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        depth--;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.SKIP_SUBTREE;
    }

    private void write(String string) {
        try {
            bufferedWriter.write(string);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String markUp(int depth, Path pathFrom) throws IOException {
        StringBuilder symbol = new StringBuilder();

        Path pathTemp = pathFrom;
        for (int i = 0; i < depth; i++) {
            List<Path> pathList = Files.list(pathTemp).filter(Files::isDirectory).collect(Collectors.toList());

            if(!pathList.isEmpty() && !pathList.get(pathList.size() - 1).equals(pathFrom)) {
                symbol.append("\t|");
            }else {
                symbol.append("\t");
            }
            if (i > 0)
                pathFrom = pathFrom.getParent();
            pathTemp = pathTemp.getParent();
        }
        return symbol.reverse().toString();
    }
}
