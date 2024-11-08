package com.diep.java.ocp17.chap10;

import java.util.function.IntUnaryOperator;

public class TicketTaker {
    private static int AT_CAPACITY = 100;
    // IntUnaryOperator functional interface is not generic,
    // so the argument IntUnaryOperator<Integer> does not compile,
    // public int takeTicket(int currentCount, IntUnaryOperator<Integer> counter) {
    public int takeTicket(int currentCount, IntUnaryOperator counter) {
        return counter.applyAsInt(currentCount);
    }
    public static void main(String[] theater) {
        final TicketTaker bob = new TicketTaker();
        final int oldCount = 50;
        final int newCount = bob.takeTicket(oldCount,t -> {
            if (t > AT_CAPACITY) {
                throw new RuntimeException("Sorry, max has been reached");
            }
            return t+1;
        });
        System.out.print(newCount);
    }

}
