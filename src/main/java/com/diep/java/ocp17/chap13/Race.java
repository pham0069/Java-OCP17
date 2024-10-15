package com.diep.java.ocp17.chap13;

import java.util.*;
import java.util.concurrent.*;

/**
 * q18
 * hare() and tortoise() are executed on 2 different threads
 * both submit tasks to service instead of executing them on main thread
 *
 * printing happens right after the task submission
 * hence the order of printing is not deterministic
 */
public class Race {
    ExecutorService service = Executors.newFixedThreadPool(8);

    public static int sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        return 1;
    }

    public void hare() {
        try {
            Callable<Integer> c = () -> sleep();
            final var r = List.of(c, c, c);
            var results = service.invokeAll(r);
            System.out.println("Hare won the race!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tortoise() {
        try {
            Callable<Integer> c = () -> sleep();
            final var r = List.of(c, c, c);
            Integer result = service.invokeAny(r); // OK
            System.out.println("Tortoise won the race!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] p) throws Exception {
        var race = new Race();
        race.service.execute(() -> race.hare());
        race.service.execute(() -> race.tortoise());
    }
}


