package com.diep.java.ocp17.chap10;

import java.util.function.Predicate;

public class Lifeguard {
    record Tourist(double distance) { }
    private void saveLife(Predicate<Tourist> canSave, Tourist tourist) {
        System.out.print(canSave.test(tourist)
                ? "Saved" : "Too far");
    }
    public final static void main(String[] sand) {
        new Lifeguard().saveLife(s -> s.distance < 4, new Tourist(2));
    }

}
