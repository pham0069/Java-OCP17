package com.diep.java.ocp17.chap13;

import java.util.concurrent.atomic.*;
import java.util.stream.*;

/**
 * Q8
 * ticketsTaken is thread-safe => consistently become 100
 * ticketsSold not thread-safe => may not print 500
 */
public class TicketTaker {
    long ticketsSold;
    final AtomicInteger ticketsTaken;
    public TicketTaker() {
        ticketsSold = 0;
        ticketsTaken = new AtomicInteger(0);
    }
    public void performJob() {
        IntStream.iterate(1, p -> p+1)
                .parallel()
                .limit(100)
                .forEach(i -> ticketsTaken.getAndIncrement());
        IntStream.iterate(1, q -> q+1)
                .parallel()
                .limit(500)
                .forEach(i -> ++ticketsSold);
        System.out.print(ticketsTaken+" "+ticketsSold);
    }
    public static void main(String[] matinee) {
        new TicketTaker().performJob();
    }
}

