package com.diep.java.ocp17.chap08;

import java.util.ArrayList;
import java.util.List;

public class CheckIfHopper implements CheckTrait {
    @Override
    public boolean test(Animal a) {
        return a.canHop();
    }

    public static void main(String[] args) {
        var animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, new CheckIfHopper());
        // alternative
        print(animals, a -> a.canHop());
    }

    static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) {
                System.out.println(animal);
            }
        }
    }
}
