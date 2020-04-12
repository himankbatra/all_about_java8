package com.himank.all_about_java8.learnjava.streams_terminal;



import com.himank.all_about_java8.learnjava.data.StudentDataBase;

import static java.util.stream.Collectors.counting;

public class StreamsCountingExample {

    public static long count() {

        return StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGpa() >= 3.9)
                .collect(counting());
    }

    public static void main(String[] args) {

        System.out.println(count());
    }
}
