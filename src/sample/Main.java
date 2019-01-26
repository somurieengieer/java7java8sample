package sample;

import sample.java7newFunc.java7sample;
import sample.java8newFunc.java8sample;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");

        System.out.println("----java7sample.tryWithResources----");
        java7sample.tryWithResources();

        System.out.println("\n----java8sample.fileReader----");
        java8sample.fileReader();
    }
}
