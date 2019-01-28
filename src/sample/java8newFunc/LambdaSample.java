package sample.java8newFunc;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaSample {

    // よく使いそうな関数型インターフェイスのサンプル
    public static void functionalInterface() {

        /* Supplier */
        Supplier<Object> sup = () -> new Object();
        System.out.println(sup.get().getClass().getName());

        /* Predicate */
        Predicate<String> pre = (s) -> s.equals("aiu");
//        Predicate<String> pre = (s) -> String::equals("aiu");
        System.out.println(pre.test("aie")); // false
        System.out.println(pre.test("aiu")); // true

        Predicate<String> isUpperCase = (s) -> s.matches("[A-Z]+");
        Predicate<String> isNumber = s -> s.matches("\\d+");
        Predicate<String> pre2 = isUpperCase.or(isNumber);
        System.out.println("Predicate OR test");
        System.out.println(pre2.test("ABC"));  // true
        System.out.println(pre2.test("123"));  // true
        System.out.println(pre2.test("ABC3")); // false
    }
}
