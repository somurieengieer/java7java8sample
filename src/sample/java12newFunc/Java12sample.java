package sample.java12newFunc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java12sample {
    public static void switchSyntax() {
        System.out.println("--アロー構文");
        int num = 4;
        switch(num) {
            case 1, 2, 3 -> System.out.println("複数条件を記述できます");
            case 4, 5, 6 -> {
                System.out.println("ブロックによる記述もできます");
            }
            default -> System.out.println("条件に合致しない場合");
        }

        System.out.println("--switch式");
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
        var list = List.of(12,532,21,2,4,5123,3,2,51,16,71,34,63,1,34,64,13);
        var result = list.stream().collect(Collectors.groupingBy(i -> i%10));
        System.out.println(result);
        var result2 = list.stream().collect(Collectors.teeing(
                Collectors.summingInt(i -> i),
                Collectors.groupingBy(i -> i%10),
                Map::entry));
        System.out.println(result2);
        // 合計値のキーに対して、1桁目でグルーピングされたMapを値に設定する
    }
}
