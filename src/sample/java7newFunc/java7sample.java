package sample.java7newFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class java7sample {

    // Java7からclose文を記載しなくても良くなった
    public static void tryWithResources() {

        // 読み込みファイル名
        String inFileName = "TestXMLFile.xml";

        // クラスパスを取得
        String classPath = System.getProperty("java.class.path") + "/sample";
        Path path = Paths.get(classPath, inFileName);
        File readFile = path.toFile();

        // Java7からtry (〜)に書かれたreader及びBufferedReader、FileReaderに対してtry句処理終了時にcloseメソッドが自動実行される
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

    // Java7からダイヤモンド演算子で型指定が省略できるようになった
    public static void omitDiamondOperator() {

        // <>の中の型を省略することができる
        List<String> lines1 = new ArrayList<>();

        // 従来の書き方
        List<String> lines2 = new ArrayList<String>();

        // 以下lines3_2のように右辺をraw型にする書き方もできるが、右辺に誤ったデータが入る可能性がある。
        List<Integer> ints3_1 = new ArrayList<>();
        List<String> lines3_2 = new ArrayList(ints3_1);  // List<Integer>が引数で定義できてしまい、実行時エラーとなる
//      List<String> lines3_3 = new ArrayList<>(ints3_1);  // <>で記載することでコンパイルエラーにかかるため、実行前に気づくことができる
    }

}
