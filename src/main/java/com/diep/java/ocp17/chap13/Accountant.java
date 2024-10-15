package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;

/**
 * q20
 * f1 is Future<Void> -> f1.get() returns null
 * f2 is Future<Object> (it's ok, double is converted to Double, and can be casted to Object)
 *
 * the code can compile and print: [Filing]null 3.14159
 */
public class Accountant {
    public static void completePaperwork() {
        System.out.print("[Filing]");
    }

    public static double getPi() {
        return 3.14159;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService x = Executors.newSingleThreadExecutor();
        Future<?> f1 = x.submit(() -> completePaperwork());
        Future<Object> f2 = x.submit(() -> getPi());
        System.out.print(f1.get() + " " + f2.get());
        x.shutdown();
    }
}

