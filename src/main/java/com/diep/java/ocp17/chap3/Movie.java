package com.diep.java.ocp17.chap3;

enum Admission {ADULT, SENIOR, CHILD}

public class Movie {
    public static void main(String[] args) {
        var price = switch (Admission.CHILD) {
            case ADULT -> 12.50;
            case SENIOR, CHILD -> 10;
        };
        System.out.println(price);
    }

    public static void test() {
        switch (Admission.CHILD) {
            case ADULT: System.out.println(12.50); break;
            case SENIOR, CHILD:  System.out.println(10.0); break;
        };
    }
}

