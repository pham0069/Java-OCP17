package com.diep.java.ocp17.chap13;

import java.util.stream.*;

/**
 * Q11
 * parallel() but forEachOrdered -> print in order
 */
public class Bull {
    void charge() {
        IntStream.range(1,6)
                .parallel()
                .forEachOrdered(System.out::print);
    }
    public static void main(String[] args) {
        var b = new Bull();
        b.charge();
    } }


