package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;

/**
 * q22
 * Synchronized block requires locking on object, e.g. synchronized(this) { }
 * synchronize { } does not compile
 *
 * when fixed with synchronized(this), the program compiles but produces a deadlock at runtime
 * due to r1 and r2 tries to obtain 2 locks at the same time but in opposite direction
 */
public class Riddle {
    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

    public String getQuestion(Riddle r) {
        //synchronized {
        synchronized (this) {
            sleep();
            if (r != null) r.getAnswer(null);
            return "How many programmers does it take "
                    + "to change a light bulb?";
        }
    }

    public synchronized String getAnswer(Riddle r) {
        sleep();
        if (r != null) r.getAnswer(null);
        return "None, that's a hardware problem";
    }

    public static void main(String... ununused) {
        var r1 = new Riddle();
        var r2 = new Riddle();
        var s = Executors.newFixedThreadPool(2);
        s.submit(() -> r1.getQuestion(r2));
        s.execute(() -> r2.getAnswer(r1));
        s.shutdown();
    }
}

