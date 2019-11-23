package com.himank.all_about_java8.ch03_stream;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamPuzzle1 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("himank");
        names.add("amit");
        names.add("harshit");
        names.add("supreet");
        names = names.subList(0, 2);
        Stream<String> stream = names.stream();
        names.add("akash");
        stream.forEach(System.out::println);

    }

}
