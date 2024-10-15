package com.diep.java.ocp17.chap4;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class PracticeTest {
    //print 4 lines as closing """ is on a new line
    @Test
    void q2() {
        System.out.print("""
                "ape"
                "baboon"
                "gorilla"
                """);
    }

    @Test
    void q3() {
        //var _ = 1; // var name can start with underscore but not be single underscore(_)
        //var 2blue = 1; // var name cannot start with number
        var _blue = 1;
        var blue$ = 1;
        var Blue = 1;
    }

    // 13/3, 2am is DST time change
    // 3 hours after 1am is 4am normally, but it passes DST,
    // so it jumps 1 hr forward, i.e. 5am
    // and offset changes
    @Test
    void q5() {
        var localDate = LocalDate.of(2022, 3, 13);
        var localTime = LocalTime.of(1, 0);
        var zone = ZoneId.of("America/New_York");
        var z = ZonedDateTime.of(localDate, localTime, zone);

        var offset = z.getOffset();
        var duration = Duration.ofHours(3);
        var later = z.plus(duration);

        System.out.println(later.getHour() + " "
                + offset.equals(later.getOffset()));
    }

    @Test
    void q7() {
        int year = 1874;
        // int month = Month.MARCH; // not compilable as enum cannot be assigned to int
        Month month = Month.MARCH;
        int day = 24;
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println(date.isBefore(LocalDate.now()));

    }

    @Test
    void q8() {
        var b = "12";
        b += "3";
        // b.reverse(); // not compiling for string, would work if b is StringBuilder
        System.out.println(b.toString());
    }

    @Test
    void q9() {
        var line = new StringBuilder("-");
        var anotherLine = line.append("-");
        System.out.print(line == anotherLine); // true
        System.out.print(" ");
        System.out.print(line.length()); // 2
    }

    @Test
    void q12() {
        var cat = Math.ceil(65); // ceil(double) -> double
        var moose = Math.max(7, 8); // max(int, int) -> int, can take long, float, double param
        var penguin = Math.pow(2, 3); // pow(double, double) -> double
        System.out.println(cat + " " + moose + " " + penguin);
    }

    @Test
    void q13() {
        var waffleDay = LocalDate.of(2022, Month.MARCH, 25);
        var period = Period.of(1, 6, 3);
        var later = waffleDay.plus(period);
        later.plusDays(1); // not taking effect on later
        var thisOne = LocalDate.of(2023, Month.SEPTEMBER, 28);
        var thatOne = LocalDate.of(2023, Month.SEPTEMBER, 29);
        System.out.println(later.isBefore(thisOne) + " " + later.isBefore(thatOne));
    }

    @Test
    void q16() {
        var line = new String("-");
        var anotherLine = line.concat("-");
        System.out.print(line == anotherLine); // false
        System.out.print(" ");
        System.out.print(line.length()); // 1
    }

    @Test
    void q22() {
        var text = """
                ant  antelope \s \n
                cat "kitten" \
                seal sea lion
                """;
        System.out.print(text);
    }

    void q33() {
        double num1 = 2.718;
        //double num2 = 2._718; // not compile as _ near .
        double num3 = 2.7_1_8;
        //double num4 = _2.718; // not compile as _ not btw 2 digits
    }

    @Test
    void q35() {
        var numPigeons_1 = Long.parseLong("100"); // long primitive
        // System.out.println(numPigeons_1.toString()); // not compile due to long primitive cannot call toString
        var numPigeons_2 = Long.valueOf("100"); // Long wrapper
        System.out.println(numPigeons_2.toString());
    }

    @Test
    void q42() {
        var trainDay = LocalDate.of(2022, 5, 13);
        var time = LocalTime.of(10, 0);
        var zone = ZoneId.of("America/Los_Angeles");
        var zdt = ZonedDateTime.of(trainDay, time, zone);
        var instant = zdt.toInstant(); // instant is specific time in GMT timezone
        instant = instant.plus(1, ChronoUnit.DAYS);
        System.out.println(instant);
    }

    @Test
    void q45() {
        var date = LocalDate.of(2022, Month.JULY, 17);
        var time = LocalTime.of(10, 0);
        var zone = ZoneId.of("America/New_York");
        var iceCreamDay = ZonedDateTime.of(date, time, zone);
        // time = time.plusMonths(1); // this does not compile as add month to time
        System.out.println(iceCreamDay.getMonthValue());
    }

    @Test
    void q48() {
        int time = 9;
        int day = 3;
        var dinner = ++time >= 10 ? day-- <= 2
                ? "Takeout" : "Salad" : "Leftovers";
        System.out.println(dinner);
    }

    /**
     * Period does not provide chaining
     * code looks like chaining due to calling static methods
     * each chained method creates a separate object
     * final object of chaining is the one created by the last call
     */
    @Test
    void q49() {
        var waffleDay = LocalDate.of(2022, Month.MARCH, 25);
        var period = Period.ofYears(1).ofMonths(6).ofDays(3); // equivalent to 3 days
        var later = waffleDay.plus(period);
        later.plusDays(1); // not taking effect
        var thisOne = LocalDate.of(2022, Month.SEPTEMBER, 28);
        var thatOne = LocalDate.of(2022, Month.SEPTEMBER, 29);
        System.out.println(later.isBefore(thisOne) + " " + later.isBefore(thatOne));
    }

    @Test
    void q51() {
        var teams = new String("694");
        //concat does not change teams
        teams.concat(" 1155");
        teams.concat(" 2265");
        teams.concat(" 2869");
        System.out.println(teams); //694
    }

    @Test
    void q53() {
        var date = LocalDate.of(2022, Month.JULY, 17);
        var time = LocalTime.of(10, 0);
        var zone = ZoneId.of("America/New_York");
        var iceCreamDay = ZonedDateTime.of(date, time, zone);
        date = date.plusMonths(1);
        System.out.println(iceCreamDay.getMonthValue());
    }

    @Test
    void q54() {
        var phrase = "prickly \nporcupine";
        System.out.println(phrase.length());
        //indent() always normalize line break, so phrase becomes "prickly \nporcupine\n"
        System.out.println(phrase.indent(1).length());// add indent for each line
        System.out.println(phrase.indent(0).length());// not add indent
        System.out.println(phrase.indent(-1).length());// remove at most 1 indent for each line
    }

    @Test
    void q56() {
        // ok
        var s_1 = """ 
                   "ape"
                   "baboon"
                   "gorilla" 
                """;

        // cannot put first line on same line as opening triple quote
        /*
        var s_2 = """ "ape"
                   "baboon"
                   "gorilla"
                """;
         */
    }

    void q57() {
        var now = LocalDate.now();
        // var instance = new LocalDate(); // no constructor
    }

    @Test
    void q58() {
        System.out.println("cheetah\ncub");
        System.out.println("cheetah\\ncub"); // \\n = \n
        System.out.println("cheetah\ncub".translateEscapes());
        System.out.println("cheetah\\ncub".translateEscapes());// \\n -> new line
    }

    @Test
    void q59() {
        int height = 2, length = 3;
        boolean w = height> 1 | --length < 4; // logical or executes both side
        var x = height!=2 ? length++ : height;
        boolean z = height % length == 0;
        System.out.println(w + "-" + x + "-" + z);
    }

    @Test
    void q63() {
        var b = Boolean.valueOf("8").booleanValue();
        // Character.valueOf('x').byteValue();
        //var d = Double.valueOf("9_.3").byteValue(); // throw exception
        var l = Long.valueOf(88).byteValue();
        System.out.println(l);
    }

    @Test
    void q69() {
        var builder = new StringBuilder("54321");
        builder.substring(2); // not changing builder
        System.out.println(builder.charAt(1));
    }

    @Test
    void q70() {
        var sum = 0.0;
        sum = sum + Math.min(3, 5);
        sum = sum + Math.floor(1.8);
        sum = sum + Math.round(5.6);//round up
        System.out.println(sum);
    }
}
