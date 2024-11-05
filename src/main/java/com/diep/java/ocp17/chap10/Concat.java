package com.diep.java.ocp17.chap10;

import java.util.List;

public class Concat {
    public String concat1(List<String> values) {
        return values.parallelStream()
                .reduce("a", (x,y)->x+y, String::concat);
    }
    public String concat2(List<String> values) {
        return values.parallelStream()
                .reduce((w,z)->z+w).get();
    }
    public static void main(String[] questions) {
        Concat c = new Concat();
        var list = List.of("Cat","Hat");
        String x = c.concat1(list);
        String y = c.concat2(list);
        System.out.print(x+" "+y);
    }

}
