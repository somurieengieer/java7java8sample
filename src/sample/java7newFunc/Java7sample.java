package sample.java7newFunc;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Java7sample {

    static class CloseSample implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("called close method ovverrided AutoClosable Implement");
        }
    }

    // Java7からclose文を記載しなくても良くなった
    public static void tryWithResources() {

        // 読み込みファイル名
        String inFileName = "TestXMLFile.xml";

        // クラスパスを取得
        String classPath = System.getProperty("java.class.path") + "/sample";
        Path path = Paths.get(classPath, inFileName);
        File readFile = path.toFile();

        // Java7からtry (〜)に書かれたreader及びBufferedReader、FileReaderに対してtry句処理終了時にcloseメソッドが自動実行される
        // java.lang.AutoCloseable か java.io.Closeableを実装しているクラスが対象
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

        // catchの省略も可能
        try (CloseSample closeSample = new CloseSample();){
            System.out.println("catchの省略");
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

    // Java7から複数のExceptionをまとめてcatchできるようになった
    public static void catchSomeException() {

        try {
            if ((new Random()).nextBoolean()) {
                throw new SQLException("test");
            } else {
                throw new IOException("test");
            }
        } catch (SQLException | IOException e) {
            System.out.println("caught error type is [" + e.getClass().getName() + "]");
        }
    }

    // Java7からリフレクションのコンパイルエラーを検出できるようになった（以前は実行時エラーのみであった）
    public static void detectReflectionError() {
        try {
            Class<?> c = Class.forName("sample.java7newFunc.Java7sample");
            Object obj = c.newInstance(); // Java9からnewInstanceメソッドは非推奨。getDeclaredConstructor().newInstance()が代替コード
            Method m = c.getMethod("printArg", String.class);
            m.invoke(obj, "called via invoke method");
        } catch (ReflectiveOperationException e) {
            System.out.println("ReflectiveOperationExceptionが検出できるようになった");
        }
    }

    public void printArg(String arg) {
        System.out.println("printArgs method is called. arg is [" + arg + "]");
    }

    // Java7からswitch文でStringを使用できるようになった
    public static void stringSwitchStatement() {
        String dayOfWeek = "Saturday";
        switch (dayOfWeek) {
            case "Sunday":
                System.out.println("Sunday");
                break;
            case "Monday":
                System.out.println("Monday");
                break;
            case "Saturday":
                System.out.println("Saturday");
                break;
            default:
                System.out.println("Else");
        }
    }

    // Java7から文字リテラルにアンダースコアが使用できるようになった
    public static void underscoreNumber() {
        int i = 123_456;
        System.out.println(i); // 123456 と表示される

        long l = 111_222_333_444L;
        System.out.println(l); // 111222333444と表示される
    }
}
