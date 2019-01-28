package sample;

import sample.java7newFunc.Java7sample;
import sample.java8newFunc.Java8sample;
import sample.java8newFunc.LambdaSample;

public class Main {

    public static void main(String[] args) {

        /* Java7 */
        System.out.println("----Java7sample.tryWithResources----");
        Java7sample.tryWithResources();

        System.out.println("\n----Java7sample.omitDiamondOperator----");
        Java7sample.omitDiamondOperator();

        System.out.println("\n----Java7sample.catchSomeException----");
        Java7sample.catchSomeException();

        System.out.println("\n----Java7sample.detectReflectionError----");
        Java7sample.detectReflectionError();

        System.out.println("\n----Java7sample.stringSwitchStatement----");
        Java7sample.stringSwitchStatement();

        System.out.println("\n----Java7sample.underscoreNumber----");
        Java7sample.underscoreNumber();

        /* Java8 */
        System.out.println("\n----Java8sample.fileReader----");
        Java8sample.fileReader();

        System.out.println("\n----Java8sample.lambda----");
        Java8sample.lambda();

        System.out.println("\n----Java8sample.stream----");
        Java8sample.stream();

        System.out.println("\n----Java8sample.convertMapAndList----");
        Java8sample.convertMapAndList();

        System.out.println("\n----Java8sample.listFunction----");
        Java8sample.listFunction();

        System.out.println("\n----Java8sample.optional----");
        Java8sample.optional();

        System.out.println("\n----Java8sample.datetimeFunction----");
        Java8sample.datetimeFunction();

        /* Thread */
        System.out.println("\n----SubThreadExtendsThread.extendsThreadSample----");
        ThreadSample.extendsThreadSample();

        /* Lambda */
        System.out.println("\n----LambdaSample.functionalInterface----");
        LambdaSample.functionalInterface();;
    }
}
