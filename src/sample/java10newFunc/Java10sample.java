package sample.java10newFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java10sample {

    private static void thisIsNotCode() {
        // GC Interfaceが作成されてGCのもジューラビリティが高くなった
    }

    public static void varSyntaxSugar() {
        var list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.stream().forEach(System.out::println);
        // ローカル変数にのみ使用可能。インスタンス変数は不可
        // ラムダ引数には使えない
    }

    public static void copyOf() {
        var list = new ArrayList<String>();
        list.add("aaa");
        var list2 = List.copyOf(list);
        list2.stream().forEach(System.out::println);

        // コピー先にaddしようとするのはNG。エラー
        // list2.add("test");

        // コピー元にaddはできる
        list.add("test");
        System.out.println("-- 追加した後のコピー元");
        list.stream().forEach(System.out::println);

        // コピー先の値は変更されない
        System.out.println("-- 追加した後のコピー先");
        list2.stream().forEach(System.out::println);
    }

    public static void orElseThrow() {
        Optional<String> opt1 = Optional.ofNullable("hoge");
        System.out.println(opt1.orElseThrow());

        try {
            Optional<String> opt2 = Optional.ofNullable(null);
            System.out.println(opt2.orElseThrow());
        } catch (Exception e) {
            System.out.println("nullのためthrowされる");
        }
    }
}
