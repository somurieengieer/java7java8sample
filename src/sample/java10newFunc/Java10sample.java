package sample.java10newFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java10sample {

    private static void thisIsNotCode() {
        // GC Interfaceが作成されてGCのモジューラビリティが高くなった
    }

    public static void varSyntaxSugar() {
        var list = new ArrayList<String>(); // listはArrayList<String>型になる
        list.add("aaa");
        list.add("bbb");
        list.stream().forEach(System.out::println);
        // ローカル変数にのみ使用可能。インスタンス変数は不可
        // ラムダ引数には使えないので注意

        var i = 10;
        var s = "aiueo";
        // プリミティブ型にも使える
    }

    public static void copyOf() {
        class Person {
            String name;
            int age;
            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
        };
        var list = new ArrayList<Person>();
        list.add(new Person("personA", 1));
        var list2 = List.copyOf(list);
        list2.stream().forEach(System.out::println);

        // コピー元のListが参照している先のオブジェクトを変更するとコピー元も変更される
        list.get(0).age = 100;
        System.out.println("-- 年齢変更後のコピー元");
        list.stream().forEach(p -> System.out.println(p.name + ", " + p.age));
        System.out.println("-- 年齢変更後のコピー先");
        list2.stream().forEach(p -> System.out.println(p.name + ", " + p.age));

        // コピー先は追加削除は不可。addするとエラー
        // list2.add(new Person("errorPerson", 2));

        // コピー元にはもちろんaddはできる
        list.add(new Person("personB", 2));
        System.out.println("-- 追加した後のコピー元");
        list.stream().forEach(p -> System.out.println(p.name + ", " + p.age));

        // コピー先の値は変更されない
        System.out.println("-- 追加した後のコピー先");
        list2.stream().forEach(p -> System.out.println(p.name + ", " + p.age));
    }

    public static void orElseThrow() {
        Optional<String> opt1 = Optional.ofNullable("hoge");
        System.out.println(opt1.orElseThrow());

        try {
            Optional<String> opt2 = Optional.ofNullable(null);
            System.out.println(opt2.orElseThrow());
        } catch (Exception e) {
            System.out.println("exception class: " + e.getClass());
            System.out.println("nullのためthrowされる");
        }
    }
}
