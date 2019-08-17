package sample.java11newFunc;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Java11sample {
    private static void thisIsNotCode() {
        // HTTP Client APIによるHTTP/2サポート
        // シングルJavaファイルからの即時実行（コンパイル不要）
        // 新しいガベージコレクション方式（Java9からG1GCがデフォルトになっているが、低スペックマシンで動作する時はSerialG1が動くみたい）
        // Javaモニタリング機能の追加（Flight Recorder）　※有償版では以前から存在していた
    }

    public static void stringIsBlank() {
        String strNull = "";
        String strLenghth0 = "";
        String strBlankChar = " ";
        String strJapaneseBlankChar = "　"; // 全角ブランク
        if (strNull.isBlank() &&
                strLenghth0.isBlank() &&
                strBlankChar.isBlank() &&
                strJapaneseBlankChar.isBlank()) {
            System.out.println("These are judged as Blank");
        }

        if (strNull.isEmpty() &&
                strLenghth0.isEmpty()) {
            System.out.println("These are judged as Empty");
        }

        // isEmpty()の場合は "" と " " がfalseになる
        if (!strBlankChar.isEmpty() &&
                !strJapaneseBlankChar.isEmpty()) {
            System.out.println("These are NOT judged as Blank");
        }
    }

    public static void collectionToArray() {
        String[] strs = List.of("aaa", "bbb").toArray(String[]::new);
        for (String str : strs) {
            System.out.println(str);
        }

        // 引数なしのtoArrayだとObject[]を返す
        Object[] objs = List.of("aaa", "bbb").toArray();
    }

    public static void optionalIsEmpty() {
        Optional<String> opt1 = Optional.ofNullable("hoge");
        if (opt1.isEmpty()) {
            System.out.println("ここには到達しない");
        }

        Optional<String> opt2 = Optional.ofNullable(null);
        if (opt2.isEmpty()) {
            System.out.println("optがnullの場合はここに到達する。isPresent()の逆条件");
        }
    }

    public static void predicateNot() {
        var list = List.of("aaa", "bbb", "", "ddd");
        list.stream().filter(Predicate.not(s -> s.isBlank())).forEach(System.out::println);
    }
}
