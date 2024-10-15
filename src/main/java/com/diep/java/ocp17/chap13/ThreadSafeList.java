package com.diep.java.ocp17.chap13;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * q26
 * the program is not thread-safe
 * synchronized(ThreadSafeList.class) -> sync on class
 * synchronized void addValue -> sync on object
 *
 */
public class ThreadSafeList {
    private List<Integer> data = new ArrayList<>();

    public synchronized void addValue(int value) {
        data.add(value);
    }

    public int getValue(int index) {
        return data.get(index);
    }

    public int size() {
        synchronized (ThreadSafeList.class) {
            int size = data.size();
            System.out.println(data.get(size-1) + " " + size);
            return data.size();
        }
    }

    public static void main(String[] args) {
        ThreadSafeList list = new ThreadSafeList();
        var executor = Executors.newFixedThreadPool(5);
        IntStream.range(0, 10).forEach(i -> {
            executor.submit(() -> list.addValue(i));
            executor.submit(() -> list.size());
        });
        executor.shutdown();
    }
}

