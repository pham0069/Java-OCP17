package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;
import java.util.stream.*;

/**
 * q30-31
 *
 * the program prints W 10 times
 * the barrier can finish 2 rounds, each round 5 tasks after all calling await will print W
 *
 *
 * service.isShutDown() -> does not shut down the service -> could have made program hang
 * even if it's changed to service.shutdown(),
 * there are 2 more tasks forever blocked -> the program still hangs
 */
public class Boat {
    private void waitTillFinished(CyclicBarrier c) {
        try {
            c.await();
            System.out.println("W");
        } catch (Exception e) {
        }
    }

    public void row(ExecutorService s) {
        var cb = new CyclicBarrier(5);
        IntStream.iterate(1, i -> i + 1)
                .limit(12)
                .forEach(i -> s.submit(() -> waitTillFinished(cb)));
    }

    public static void main(String[] oars) {
        ExecutorService service = null;
        try {
            service = Executors.newCachedThreadPool();
            new Boat().row(service);
        } finally {
            service.isShutdown();
        }
    }
}


