package com.diep.java.ocp17.chap3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PracticeTest {
    @Test
    void q1() {
        Penguins.main(null);
    }

    // this will loop forever as when m== 3, the loop keeps continuing
    @Test
    void q5() {
        int m = 0, n = 0;
        while (m < 5) {
            n++;
            if (m == 3)
                continue;

            switch (m) {
                case 0:
                case 1:
                    n++;
                default:
                    n++;
            }
            m++;
        }
        System.out.println(m + " " + n);
    }

    // case returns int and double -> var becomes double
    // 10 is casted to 10.0
    // note that case SENIOR, CHILD is allowed in Java 17 (for both switch statement and expression)
    @Test
    void q6() {
        Movie.main(null);
    }

    @Test
    void q10() {
        int magicNumber = 7;
        var ok = switch (magicNumber) {
            case 7 -> true;              //true;  break;
            case 9 -> {
                yield true;
            }    //{ yield true }
            case 11 -> {
                yield true;
            }   //yield true;
            case 13 -> {
                yield true;
            }   //: {yield true;}
            default -> false;
        };
    }

    @Test
    void q12() {
        Object number = 100;
        if (number instanceof Integer n && Math.abs(n) == 0)
            System.out.println("zero");
        else
            System.out.println("non-zero");
    }

    @Test
    void q14() {
        var race = "";
        loop:
        do {
            race += "x";
            break loop;
        } while (true);
        System.out.println(race);
    }

    @Test
    void q15() {
        // switch expression (see arrow) may not need to return any value
        // i.e. not all cases must be covered, i.e. default might be omitted
        // switch expression does not let fall through even without break
        int count = 0;
        char letter = 'A';
        switch (letter) {
            case 'A' -> count++;
            case 'B' -> count++;
        }
        System.out.println(count);
    }

    @Test
    void q17() {
        var bottles = List.of("glass", "plastic", "can");
        for (int type = 1; type < bottles.size(); ) {
            System.out.print(bottles.get(type) + "-");
            if (type < bottles.size()) break;
        }
        System.out.print("end");
    }

    @Test
    void q19() {
        int pterodactyl = 8;
        long triceratops = 3;
        if (pterodactyl % 3 > 1 + 1)
            triceratops++;
        triceratops--;
        System.out.print(triceratops);
    }

    @Test
    void q20() {
        int colorOfRainbow = 10;
        //int red = 5;          // not work as var is not declared final
        //final int red = 5;    // work, final and matching type with colorOfRainbow
        //final long red = 5;   // type not matching colorOfRainbow
        final var red = 5;      // also works with var, as long as final is declared

        switch (colorOfRainbow) {
            default:
                System.out.print("Home");
                break;
            case red:
                System.out.print("Away");
        }
    }

    @Test
    void q21$() {
        Collection<String> kitties = new HashSet<>();
        if (kitties instanceof List c) {
            System.out.println("L " + c.size());
        } else if (kitties instanceof Set c) {
            c = new TreeSet<>(); // ~ c = (Set) kitties -> c can be reassigned without affecting kitties
            System.out.println("M " + c.getClass() + " " + kitties.getClass());
        }
    }

    @Test
    void q26() {
        Object obj = LocalTime.now();
        if (!(obj instanceof LocalDate date))
            return;
        System.out.println(date);
    }
}
