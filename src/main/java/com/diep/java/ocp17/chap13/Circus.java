package com.diep.java.ocp17.chap13;

import java.util.concurrent.atomic.*;
import java.util.stream.*;

/**
 * q17
 * seal not thread-safe
 * tiger allows flush to see correct last value but cannot prevent wrong concurrent update
 * seal and tiger are not thread-safe -> final result <= 100
 * lion thread-safe -> expected 100
 */
public class Circus {
    private static int seal = 0;
    private static volatile int tiger = 0;
    private static AtomicInteger lion = new AtomicInteger(0);
    public static void main(String[] tent) {
        Stream.iterate(0, x -> x + 1)
                .parallel()
                .limit(100)
                .forEach(q -> {seal++; tiger++; lion.incrementAndGet();});
        System.out.println(seal + "," + tiger + ","+ lion);
    }
}

