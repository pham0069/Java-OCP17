package com.diep.java.ocp17.chap13;

import java.util.concurrent.*;

/**
 * q14
 * c.await() causes the thread to be blocked until the cyclic barrier is tripped
 * (i.e. 4 threads call c.await()
 * however, the service only has 1 thread -> other 3 threads cannot execute await
 * hence, the program is blocking forerver
 * though the service is called shutdown, but there are 11 tasks already submitted and blocked
 * the program cannot end and Ready is never printed
 *
 * the result would be the same if s = Executors.newFixedThreadPool(x); where x < 4
 *
 * if x >= 4, we can have 4 threads executing await to trigger cyclic barrier tripped
 * as 12 tasks submitted, Ready is printed 3 times and program ends
 *
 * if number of tasks is not divisible by 4, e.g. 10, the program cannot end
 * as the last batch of tasks has less than 4 tasks and cannot trigger the trip
 */
public class CartoonCat {
    private void await(CyclicBarrier c) {
        try {
            c.await();
        } catch (Exception e) {}
    }
    public void march(CyclicBarrier c) {
        var s = Executors.newSingleThreadExecutor();
        //var s = Executors.newFixedThreadPool(4);
        for(int i=0; i<12; i++)
            s.execute(() -> await(c));
        s.shutdown();
    }
    public static void main(String... strings) {
        new CartoonCat().march(new CyclicBarrier(4,
                () -> System.out.println("Ready")));
    } }

