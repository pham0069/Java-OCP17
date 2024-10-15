package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;

/**
 * q24
 * synchronized -> thread-safe
 * always print 1000
 */
public class Athlete {
    int stroke = 0;

    public synchronized void swimming() {
        stroke++;
    }

    private int getStroke() {
        synchronized (this) {
            return stroke;
        }
    }

    public static void main(String... laps) {
        ExecutorService s = Executors.newFixedThreadPool(10);
        Athlete a = new Athlete();
        for (int i = 0; i < 1000; i++) {
            s.execute(() -> a.swimming());
        }
        s.shutdown();
        System.out.print(a.getStroke());
    }
}

