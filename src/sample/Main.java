package sample;

import sample.designpattern.builder.IceCream;
import sample.designpattern.builder.StrawberryIceCream;
import sample.java10newFunc.Java10sample;
import sample.java7newFunc.Java7sample;
import sample.java8newFunc.Java8sample;
import sample.java8newFunc.LambdaSample;
import sample.java8newFunc.OptionalSample;
import sample.java9newFunc.Java9sample;

public class Main {

    public static void main(String[] args) {

        callJava7Func();
        callJava8Func();
        callThreadFunc();
        callLambdaFunc();
        callBuilderPatternFunc();
        callOptionalFunc();
        callJava9Func();
        callJava10Func();
    }

    private static void callJava10Func() {
        /* Java10 */
        System.out.println("\n----Java10sample.varSyntaxSugar----");
        Java10sample.varSyntaxSugar();
        System.out.println("\n----Java10sample.copyOf----");
        Java10sample.copyOf();
        System.out.println("\n----Java10sample.orElseThrow----");
        Java10sample.orElseThrow();

    }

    private static void callJava9Func() {
        /* Java9 */
        System.out.println("\n----Java9sample.stringChars----");
        Java9sample.stringChars();
        System.out.println("\n----Java9sample.sqrt----");
        Java9sample.sqrt();
        System.out.println("\n----Java9sample.ifPresentOrElse----");
        Java9sample.ifPresentOrElse();
        System.out.println("\n----Java9sample.streamNullFilter----");
        Java9sample.streamNullFilter();
        System.out.println("\n----Java9sample.optionOr----");
        Java9sample.optionOr();
        System.out.println("\n----Java9sample.listOf----");
        Java9sample.listOf();
        System.out.println("\n----Java9sample.streamTakeWhile----");
        Java9sample.streamTakeWhile();
        System.out.println("\n----Java9sample.streamDropWhile----");
        Java9sample.streamDropWhile();
    }

    private static void callOptionalFunc() {
        System.out.println("\n----Optional Sample----");
        OptionalSample sample = new OptionalSample();
        sample.call();
    }

    private static void callBuilderPatternFunc() {
        /* Builder */
        System.out.println("\n----BuilderPattern----");
        IceCream.Builder builder = new StrawberryIceCream.Builder();
        builder.addTopping(IceCream.Topping.CHOCOLATE).addTopping(IceCream.Topping.WHIPPEDCREAM);
        IceCream ice = new StrawberryIceCream(builder);
        System.out.println("Toppings are " + ice.taste());
    }


    private static void callLambdaFunc() {
        /* Lambda */
        System.out.println("\n----LambdaSample.functionalInterface----");
        LambdaSample.functionalInterface();;
    }

    private static void callThreadFunc() {
        /* Thread */
        System.out.println("\n----SubThreadExtendsThread.extendsThreadSample----");
        ThreadSample.extendsThreadSample();
    }

    private static void callJava8Func() {
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
    }

    private static void callJava7Func() {
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
    }
}
