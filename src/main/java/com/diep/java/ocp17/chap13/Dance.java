package com.diep.java.ocp17.chap13;

import java.util.*;

/**
 * Q6
 * t.interrupt() does not affect thread t if it's new or working
 * it only affects if t is sleeping
 * class is thread-safe -> always print 10
 */
public class Dance {
    int count = 0;
    synchronized int step() { return count++; }
    public static void main(String[] args) throws InterruptedException {
        var waltz = new Dance();
        var dancers = new ArrayList<Thread>();

        for(int i=0; i<10; i++)
            dancers.add(new Thread(() -> waltz.step()));
        for(Thread dancer : dancers)
            dancer.start();
        dancers.forEach(d -> d.interrupt());

        Thread.sleep(5_000);
        System.out.print(waltz.count);
    }
}


