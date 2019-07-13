package sample.designpattern.builder;

import java.util.stream.Collectors;

public class StrawberryIceCream extends IceCream {

    public static class Builder extends IceCream.Builder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }
    }

    public StrawberryIceCream(IceCream.Builder<?> builder) {
        super(builder);
    }

    @Override
    public String taste() {
        return toppings.stream().map(t -> t.name()).collect(Collectors.joining(", "));
    }
}
