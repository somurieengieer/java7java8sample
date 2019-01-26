package sample.java8newFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class java8sample {

    // Java8からファイル全行読み込みが楽になった
    public static void fileReader() {

        // 読み込みファイル名
        String inFileName = "TestXMLFile.xml";

        // クラスパスを取得
        String classPath = System.getProperty("java.class.path") + "/sample";
        Path path = Paths.get(classPath, inFileName);

        // ----------- Java8のファイル読み込み -------------
        try {
            // Java8からFiles.readAllLinesができた
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            // 以下の書き方でStream<String>として返すことも可能
            // List<String> lines = Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.toList());
            for(String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ----------- Java8以前のファイル読み込み ------------
        System.out.println("\nJava8以前の書き方");
        File readFile = path.toFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFile));){
            String str;
            List<String> lines = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                lines.add(str);
            }
            for(String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
