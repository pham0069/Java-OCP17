package com.diep.java.ocp17.chap13;

/**
 * Q1
 * t.run() -> execute on main thread
 * t.interrupt() -> interrupt thread t
 * hence main thread is not interrupted, i.e. no exception => not print anything
 */
public class Speaking {
    void talk() {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println("How rude!");
        }
    }
    public static void main(String[] args) {
        var s = new Speaking();
        var t = new Thread(() -> s.talk());
        t.run();
        t.interrupt();
        t.interrupt();
        t.interrupt();
    }
}

