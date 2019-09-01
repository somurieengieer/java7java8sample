package sample.java11newFunc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Java11sample {
    private static void thisIsNotCode() {
        // HTTP Client APIによるHTTP/2サポート
        // シングルJavaファイルからの即時実行（コンパイル不要）
        // 新しいガベージコレクション方式ZGCの追加（Java9からG1GCがデフォルトになっているが、低スペックマシンで動作する時はSerialG1が動くみたい）
        // Javaモニタリング機能の追加（Flight Recorder）　※有償版では以前から存在していた
    }

    public static void stringLines() {
        String multiLines = "line1\nline2\nline3";
        multiLines.lines().forEach(System.out::println);
        // 改行で文字列を分割てStream<String>型で返します。
    }

    public static void stringIsBlank() {
        String strBlank = "";
        String strBlankChar = " ";
        String strJapaneseBlankChar = "　"; // 全角ブランク

        // isBlankでは""、" "、"　"の全てにおいてtrueを返す
        if (strBlank.isBlank() &&
                strBlankChar.isBlank() &&
                strJapaneseBlankChar.isBlank()) {
            System.out.println("These are judged as Blank");
        }

        // isEmptyでは""のみtrueを返す
        if (strBlank.isEmpty()) {
            System.out.println("These are judged as Empty");
        }

        // isEmptyでは""、" "の場合falseを返す
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
        // filterによってブランクでない"aaa", "bbb", "ddd"に絞られます
    }

    public static void http2() {

        // クライアントを定義。プロキシ設定やhttpバージョンを指定できる。デフォルトでhttp2を使う
        var client = HttpClient.newHttpClient();

        // リクエストを定義。POSTパラメータやヘッダ設定などもここで行う
        var request = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                .GET().build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body().substring(0, 3000));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
