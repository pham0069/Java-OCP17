package com.diep.java.ocp17.chap13;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class PracticeTest {

    @Test
    void q1() {
        Speaking.main(null);
    }
    @Test
    void q3() {
        Callable c = () -> "hello";
        Runnable r = () -> System.out.println("hello");
        ExecutorService s = Executors.newScheduledThreadPool(1);

        // non-exist method on ExecutorService
        //s.execute(c);
        //s.exit();
        //s.halt();
        //s.shutdownAndTerminate();
        s.shutdownNow();
        s.shutdown();
        s.submit(r);
        s.submit(c);
        s.execute(r);
    }

    // OK
    @Test
    void q9_1() {
        var original = List.of(1,2,3,4,5);

        var copy1 = new CopyOnWriteArrayList<>(original);
        for(Integer w : copy1)
            copy1.remove(w);
    }

    // throw UnsupportedOperationException as copy2 is immutable list
    @Test
    void q9_2() {
        var original = List.of(1,2,3,4,5);

        var copy2 = Collections.synchronizedList(original);
        for(Integer w : copy2)
            copy2.remove(w);
    }

    // throw ConcurrentModificationException as iterating and remove at same time
    @Test
    void q9_3() {
        var original = List.of(1,2,3,4,5);

        var copy3 = new ArrayList<>(original);
        for(Integer w : copy3)
            copy3.remove(w);
    }

    //OK
    @Test
    void q9_4() {
        var original = List.of(1,2,3,4,5);

        var copy4 = new ConcurrentLinkedQueue<Integer>(original);
        for(Integer w : copy4)
            copy4.remove(w);

    }

    @Test
    void q13() {
        // non-exist Executors static methods
        //Executors.newFixedScheduledThreadPool()
        //Executors.newThreadPool()
        Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(5); // fixed number of threads
        Executors.newSingleThreadScheduledExecutor();   // single thread

    }

    void q19() {
        ConcurrentSkipListMap map; // skip ~ sorted/navigable
        ConcurrentSkipListSet set;
        // ConcurrentSkipList list; // not exist
    }

    /**
     * ScheduleAtFixedRate() might result 2 actions executed at the same time
     * if the first action takes too long to complete,
     * but the second action is already scheduled to take place
     */
    void q23() {
        var s = Executors.newSingleThreadScheduledExecutor();

        // return ScheduledFuture<>, only works with Runnable
        s.scheduleAtFixedRate(() -> {}, 0, 1, TimeUnit.MINUTES);
        s.scheduleWithFixedDelay(() -> {}, 0, 1, TimeUnit.MINUTES);

        // return ScheduledFuture<>, works with Runnable and Callable
        s.schedule(() -> {}, 1, TimeUnit.MINUTES);
        s.schedule(() -> 1, 1, TimeUnit.MINUTES);
        // s.scheduleAtFixedDelay() // not exist
        //s.scheduleAtSameRate()
        //s.scheduleWithRate()
    }

    @Test
    void q24() {
        Athlete.main(null);
    }

    @Test
    void q26() {
        ThreadSafeList.main(null);
    }

    @Test
    void q29() {
        ScheduledExecutorService t = Executors.newSingleThreadScheduledExecutor();
        // Future result = t.execute(System.out::println);
        // t.invokeAll(null);
        // t.scheduleAtFixedRate(() -> {return;},5,TimeUnit.MINUTES);
    }


    @Test
    void q32() {
        var db = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(1,6)
                .parallel()
                .map(i -> {db.add(i); return i;})
                .forEachOrdered(System.out::print);  // p1
        System.out.println();
        db.forEach(System.out::print);          // p2

    }

    @Test
    void q34_1() {
        var original = new ArrayList<Integer>(List.of(1,2,3));

        var copy1 = new ArrayList<Integer>(original);
        for(Integer q : copy1)
            copy1.add(1);
    }

    @Test
    void q34_2() {
        var original = new ArrayList<Integer>(List.of(1,2,3));

        var copy2 = new CopyOnWriteArrayList<Integer>(original);
        for(Integer q : copy2)
            copy2.add(2);
    }

    @Test
    void q34_3() {
        var original = new ArrayList<Integer>(List.of(1,2,3));

        var copy3 = new LinkedBlockingQueue<Integer>(original);
        for(Integer q : copy3)
            copy3.offer(3);
    }

    @Test
    void q34_4() {
        var original = new ArrayList<Integer>(List.of(1,2,3));

        var copy4 = Collections.synchronizedList(original);
        for(Integer q : copy4)
            copy4.add(4);
    }
}
