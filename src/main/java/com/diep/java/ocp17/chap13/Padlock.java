package com.diep.java.ocp17.chap13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Q7
 * tryLock() if true then lock() again => acquire lock 2 times
 * it would need to unlock() 2 times to release the lock properly
 *
 * if unlock() 1 time -> other threads cannot obtain the lock with tryLock()
 * and they just return immediately (not blocked forever as when using lock())
 * hence it's printed once
 *
 * if unlock() 2 times -> other threads are likely to obtain the lock
 * (as there is sleep between threads start)
 * and the printing can occur for 5 times as expected
 */
public class Padlock {
    private Lock lock = new ReentrantLock();
    public void lockUp() {
        if (lock.tryLock()) {
            lock.lock();
            System.out.println("Locked!");
            //lock.unlock();
            lock.unlock();
        }
    }
    public static void main(String... unused) throws Exception {
        var gate = new Padlock();
        for(int i=0; i<5; i++) {
            new Thread(() -> gate.lockUp()).start();
            Thread.sleep(1_000);
        }
    }
}

