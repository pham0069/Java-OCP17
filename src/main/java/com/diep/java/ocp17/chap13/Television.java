package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * q33
 * Lock only got tryLock(duration, durationUnit), not lock with duration
 */
public class Television {
    private static Lock myTurn = new ReentrantLock();

    public void watch() {
        try {
            //if (myTurn.lock(5, TimeUnit.SECONDS)) {
            if (myTurn.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("TV Time");
                myTurn.unlock();
            }
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] t) throws Exception {
        var newTv = new Television();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> newTv.watch()).start();
            Thread.sleep(10 * 1000);
        }
    }
}

