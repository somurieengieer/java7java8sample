package sample.java8newFunc;

import java.util.Optional;

class Place {
    String address = "";

    public Place(String address) {
        this.address = address;
    }

    public String address() {
        return address;
    }
}

public class OptionalSample {

    public void call() {

        System.out.println("Placeがnullでない場合");
        Place p = new Place("値あり");

        System.out.println("・getによる呼び出し");
        System.out.println(Optional.ofNullable(p).get().address());

        System.out.println("・orElseによる呼び出し");
        System.out.println(Optional.ofNullable(p).orElse(new Place("値なし！")).address());

        System.out.println("Placeがnullの場合");
        Place pNull = null;
        System.out.println("・orElseによる呼び出し");
        System.out.println(Optional.ofNullable(pNull).orElse(new Place("値なし！")).address());
    }
}
