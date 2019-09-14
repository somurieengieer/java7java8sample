package sample.java12newFunc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java12sample {
    public static void switchSyntax() {
        System.out.println("-- アロー構文 --");
        int num = 4;
        switch(num) {
            case 1, 2, 3 -> System.out.println("複数条件を記述できます");
            case 4, 5, 6 -> {
                System.out.println("複数行の処理を書くときには");
                System.out.println("ブロックで書きます");
            }
            default -> System.out.println("条件に合致しない場合");
        }

        System.out.println("-- switch式 --");
        String message = switch (num) {
            case 1, 2, 3 -> "複数条件を記述できます";
            case 4, 5, 6 -> {
                System.out.println("ブロックの場合はbreakにより値を返却する");
                break "ブロックによる記述もできます";
            }
            default -> "条件に合致しない場合";
        };
        System.out.println(message);
    }

    public static void stringTransform() {
        var userMap = Map.of("id111", "一太郎", "id222", "二太郎");

        // キー、メソッド名の順に記載ができるます
        System.out.println("id111".transform(userMap::get));
    }

    public static void collectorsTeeing() {
        var intList = List.of(12,32,21,13);
        // 値の範囲を文字列で出力する
        String result =
        intList.stream().collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (min, max) -> min.get() + " ~ " + max.get()));
        System.out.println(result);
    }
}
