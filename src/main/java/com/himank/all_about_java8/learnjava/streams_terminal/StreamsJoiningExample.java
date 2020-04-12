package com.himank.all_about_java8.learnjava.streams_terminal;


import com.himank.all_about_java8.learnjava.data.Student;
import com.himank.all_about_java8.learnjava.data.StudentDataBase;

import static java.util.stream.Collectors.joining;

public class StreamsJoiningExample {

    public static String joining_1(){

       return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)//<Stream<String>>
                .collect(joining());
    }

    public static String joining_2(){

        return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)//<Stream<String>>
                .collect(joining("-"));
    }

    public static String joining_3(){

        return StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)//<Stream<String>>
                .collect(joining("-","(",")"));
    }

    public static void main(String[] args) {

        System.out.println("joining_1 : " + joining_1());

        System.out.println("joining_2 : " + joining_2());

        System.out.println("joining_3 : " + joining_3());
    }
}
