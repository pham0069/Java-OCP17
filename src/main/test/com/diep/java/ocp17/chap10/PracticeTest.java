package com.diep.java.ocp17.chap10;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PracticeTest {
    @Test
    void q5() {
        var list = List.of("Austin", "Boston", "San Francisco");
        var c = list.stream()
                .filter(a -> a.length()> 10)  // line x
                .count();
        System.out.println(c + " " + list.size());
    }
    @Test
    void q10() {
        var odds = IntStream.iterate(1, a -> a+2);
        var evens = IntStream.iterate(2, a -> a+2);
        var sum = IntStream.concat(odds, evens).limit(3).sum();
        System.out.print(sum);
    }
    @Test
    void q14() {
        ToIntFunction<Integer> transformer = x -> x;

        var prime = List.of(3,1,4,1,5,9)
                .stream()
                .limit(1)
                .peek(s -> {})
                .mapToInt(transformer)
                .peek(s -> {})
                .sum();
        System.out.println(prime);
    }

    @Test
    void q15() {
        var stream = Stream.of(1, 2, 3);
        System.out.print(stream.min((a, b) -> a - b));
    }
    @Test
    void q28() {
        Set<String> set = new HashSet<>();
        set.add("tire-");
        List<String> list = new LinkedList<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.push("wheel-");
        Stream.of(set, list, queue)
            .flatMap(x -> x.stream()) // function convert T to stream
            .forEach(System.out::print);

    }
    @Test
    void q30() {
        TicketTaker.main(null);
    }
    @Test
    void q32() {
        //Predicate dash = c -> c.startsWith("-"); // not work for Predicate<Object>
        Predicate<String> dash = c -> c.startsWith("-"); // ok if specify generic type
        System.out.println(dash.test("–"));

        Consumer clear = x -> System.out.println(x);
        clear.accept("pink");
        Comparator<String> c = (String s, String t) -> 0;
        System.out.println(c.compare("s", "t"));

    }

    @Test
    void q41() {
        Concat.main(null);
    }
    @Test
    void q45() {
        var chars = Stream.generate(() -> 'a');
        chars.filter(c -> c < 'b')
                .sorted()
                .findFirst()
                .ifPresent(System.out::print);

    }
    @Test
    void q48() {
        var ints = IntStream.of(6, 10);
        var longs = ints.mapToLong(i -> i);
        var first = longs.average().getAsDouble();

        var moreLongs = LongStream.of(6, 10);
        var stats = moreLongs.summaryStatistics();
        var second = stats.getAverage();
        System.out.println("*" + first + "-" + second + "*");

    }
    @Test
    void q54() {
        var s = Stream.of("speak", "bark", "meow", "growl");
        BinaryOperator<String> merge = (a, b) -> a;
        var map = s.collect(Collectors.toMap(String::length, k -> k, merge));
        System.out.println(map);
    }
    @Test
    void q59() {
        Doll.main(null); // run forever as layer never changes
    }
    @Test
    void q60() {
        Random r = new Random();
        Stream.generate(r::nextDouble)
                .skip(2)
                .limit(4)
                .sorted()
                .peek(System.out::println)
                .forEach(System.out::println);
    }
    @Test
    void q64() {
        List.of(2,4,6,8)
            //    .parallel() // not compile
                .parallelStream()
                .forEach(System.out::print);

        List.of(2,4,6,8)
                .parallelStream()
                .parallel() // not necessary
                .forEach(System.out::print);

        List.of(2,4,6,8)
                .parallelStream()
                .parallel().parallel().parallel()
                .forEach(System.out::print);

    }
    @Test
    void q65() {
        //BiPredicate<String, String> pred = (a, b) -> a.contains(b);
        BiPredicate<String, String> pred = String::contains;
        System.out.println(pred.test("fish", "is"));

    }
    @Test
    void q66() {
        Speaking.main(null);
    }
    @Test
    void q69() {
        var data = List.of(1,2,3);
        int f = data.parallelStream().reduce(1, (a,b) -> a+b, (a,b) -> a+b);
        System.out.println(f);
    }

    @Test
    void q74() {
        Lifeguard.main(null);
    }
    @Test
    void q75() {
        List<Integer> list = List.of(1, 2, 3);
        list.parallelStream().forEach(System.out::println);
    }
}
