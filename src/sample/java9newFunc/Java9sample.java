package sample.java9newFunc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java9sample {

   private static void thisIsNotCode() {
      // モジュール機能が追加された（パッケージの上位概念）
      // Jshell機能が追加された
   }

   public static void stringChars() {
      String str = "ABCEマート";
      str.chars().mapToObj(c -> (char)c).forEach(System.out::println);
      // chars()の返り値はIntStreamのため、明示的なcharsへの型変換が必要
   }


   public static void sqrt() {
      BigInteger bi1 = new BigInteger("9");
      System.out.println(bi1.sqrt());
      BigInteger bi2 = new BigInteger("2");
      System.out.println(bi2.sqrt());
      // sqrt()の返り値はBigIntegerのため、四捨五入されて1が返却される
   }

   public static void ifPresentOrElse() {
      Optional<String> opt1 = Optional.ofNullable("hoge");
      opt1.ifPresentOrElse(
              s -> System.out.println(s + " is exist"),
              () -> System.out.println("not exist")
      );
      Optional<String> opt2 = Optional.ofNullable(null);
      opt2.ifPresentOrElse(
              s -> System.out.println(s + " is exist"),
              () -> System.out.println("not exist")
      );
   }

   public static void streamNullFilter() {
      List<String> searchList = new ArrayList<>(){
         {
            add("aaa");
            add("ddd");
            add(null);
         }};
      List<Optional<String>> optList = searchList.stream().map(s -> Optional.ofNullable(s)).collect(Collectors.toList());
      optList.stream().flatMap(Optional::stream).forEach(System.out::println);
      // flatMap(Optional::stream)を使うことで、楽にnullを排除できるようになった
      // 以前は.filter(Optional::isPresent).map(Optional::get)と書かなければいけなかった

      List<String> searchList2 = List.of("aaa", "bbb", "ccc", "ddd");
      Map<String, String> resultMap = new HashMap<>(){{
         put("aaa", "exist_a");
         put("ddd", "exist_b");
      }};
      searchList2.stream().flatMap(s -> Stream.ofNullable(resultMap.get(s)))
              .forEach(s -> System.out.println(s));
      // このように"bbb"と"ccc”に紐づく値は取得できないためstreamから外すことができる
   }

   public static void optionOr() {
      Optional opt1 = Optional.ofNullable("hoge");
      System.out.println(opt1.or(() -> Optional.of("not exist")).get());
      Optional opt2 = Optional.ofNullable(null);
      System.out.println(opt2.or(() -> Optional.of("not exist")).get());
      // opt2がnullの場合、or内の処理結果を使用する
   }

   public static void listOf() {
      List list = List.of("aaa", "bbb", "ccc");
      list.stream().forEach(System.out::println);
      // イミュータブルのため注意
      // list.add("add is not allowed"); <- throw UnsupportedOperationException
   }

   public static void streamTakeWhile() {
      List<Integer> list = List.of(3,5,1,3,8,8,2,4,2,3,5,8);
      list.stream().takeWhile(i -> i < 6).forEach(System.out::println);
      // i < 6の条件がfalseになるまでの値のstreamを返す
   }

   public static void streamDropWhile() {
      List<Integer> list = List.of(3,5,1,3,8,8,2,4,2,3,5,8);
      list.stream().dropWhile(i -> i > 2).forEach(System.out::println);
      // i < 3の条件がtrueの間の値を削除したstreamを返す
   }
}


