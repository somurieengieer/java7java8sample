package sample.java8newFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8sample {

    // Java8からファイル全行読み込みが楽になった
    public static void fileReader() {

        // 読み込みファイル名
        String inFileName = "TestXMLFile.xml";

        // クラスパスを取得
        String classPath = System.getProperty("java.class.path") + "/sample";
        Path path = Paths.get(classPath, inFileName);

        // ----------- Java8のファイル読み込み -------------
        System.out.println("\nJava8の書き方1");
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

        System.out.println("\nJava8の書き方2");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
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

    // Java8からLambda式が使えるようになった
    public static void lambda() {

        /*
        Lambda式まとめ
        ・Java8では抽象メソッドを1つのみもつインターフェイスを関数型インターフェイスと呼ぶ。慣習として@FunctionalInterfaceを付ける
        　※但し、staticメソッド、defaultメソッド、java.lang.Objectのpublicメソッドは記載可能
        ・関数型インターフェイスはjava.util.functionにある　一覧->　https://docs.oracle.com/javase/jp/8/docs/api/java/util/function/package-summary.html
        ・代表的な使い方は以下の通り
        　　・Collections.sortで処理を動的に変更したいとき（sortルールを記載したい）
        　　・StreamAPIでListやMapにまとめて処理をしたい
         */

        // Listのループ処理（ListにforEachがJava8で追加された）
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("ccc");
        list.add("bbb");

        /* Listループの比較 */
        System.out.println("Listループの比較");
        // Lambda式パターン
        list.forEach(str -> System.out.println(str));
        list.forEach(System.out::println);

        // 従来のパターン
        for(String str : list) {
            System.out.println(str);
        }

        /* 無名クラスを一時的に用意する処理（Comparatorのパターン） */
        System.out.println("無名クラスを一時的に用意する処理");
        String[] ary1 = list.toArray(new String[list.size()]);
        String[] ary2 = list.toArray(new String[list.size()]);

        // Lambda式パターン
        Arrays.sort(ary1, (a, b) -> a.compareToIgnoreCase(b));
        Arrays.asList(ary1).forEach(str -> System.out.println(str));

        // 従来のパターン
        Arrays.sort(ary2, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareToIgnoreCase(b);
            }
        });
        for (String str : ary2) {
            System.out.println(str);
        }

        /* その他のLambda式書き方（複数行処理） */
        Arrays.asList(ary1).forEach(str -> {
            System.out.print("複数行処理 ");
            System.out.println(str);
        });
    }

    // Java8からStream機能が追加された。要素の処理を簡潔に実行できる
    public static void stream() {
//        Integer[] a = {1,2,4,5,3,7};
//        Integer[] b = new Integer[]{1,2,4,5,3,7};
        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 4, 5, 3, 7});
        System.out.println("Listの中身を3以上の値のみ出力(filter)");
        list.stream().filter((i) -> i >= 3).forEach(System.out::println);

        System.out.println("Listの中身をソートして出力(sorted)");
        list.stream().sorted((a, b) -> a - b).forEach(System.out::println);

        System.out.println("Listの中身を全て四則演算(map)");
        list.stream().map(x -> x * 10).forEach(System.out::println);

        System.out.println("a1かb1の値のみ出力(filter)");
        List<String> strList = new ArrayList<>(Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2", "a3" ));
        strList.stream().filter((s) -> s.matches("[a-b]1")).forEach(System.out::println);
    }

    // MapとListの変換
    public static void convertMapAndList() {

        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 4, 5, 3, 7});
        System.out.println(list);

        System.out.println("ListからMapへ変換");
        Map<Integer, Integer> map = list.stream().collect(Collectors.toMap(s -> s, s -> s * 10));
        System.out.println(map.toString());

        System.out.println("Mapのグルーピング処理");
        List<String> strList = new ArrayList<>(Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2", "a3" ));
        Map<Integer, List<String>> iMap = strList.stream().collect(
            Collectors.groupingBy(s -> {
                    return Character.getNumericValue(s.charAt(1)) + 10;  // これがMapのKeyになる
            })
        );
        System.out.println(iMap.toString());
        iMap.forEach((key, value) -> {
            System.out.print("key: " + key + ", value: ");
            value.forEach(s -> System.out.print(s + " "));
            System.out.println();
        });
    }

    // Listの複雑な処理
    public static void listFunction() {

        IntStream.generate(() -> (int)(Math.random() * 100))
                .filter(n -> n >= 77)
                .distinct()
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
    }

    // Optional機能
    public static void optional() {

        // 右辺を関数とし、関数の返り値に対してnullだった場合の処理を1行で記載することができる
        Optional<String> optTarget = Optional.ofNullable("aaa");
        Optional<String> opt = optTarget;

        // 値がnullの場合はenを返す
        String result = opt.orElse("en");
        System.out.println(result);

        // 値を保つ場合のみ実行
        opt.ifPresent((s) -> System.out.println("値あり -> " + s));
    }

    // 日付処理にLocalDateTime（タイムゾーン情報を持たない）とZonedDateTime（タイムゾーン情報を持つ）が追加された
    public static void datetimeFunction() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()); // YYYY-mm-dd （mmとddはゼロ詰めなし）
        System.out.println(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond()); // HH:MM:SS
    }
}
