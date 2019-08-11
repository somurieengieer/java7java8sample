package sample.designpattern.builder;

import java.util.EnumSet;
import java.util.Set;

public abstract class IceCream {
    public enum Topping {
        CHOCOLATE,
        CORN,
        WHIPPEDCREAM,
    }
    final Set<Topping> toppings;

    public abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self(); // return this;にしたいが、thisにすると返り値を継承クラスにできない
        }

        protected abstract T self();
    }

    public IceCream(Builder<?> builder) {
        toppings = builder.toppings.clone();
        System.out.println("Create IceCream.");
    }

    public abstract String taste();
}
