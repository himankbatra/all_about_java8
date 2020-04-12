package com.himank.all_about_java8.learnjava.streams_terminal;

import com.himank.all_about_java8.learnjava.data.Student;
import com.himank.all_about_java8.learnjava.data.StudentDataBase;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.summingInt;

public class StreamsSumAvgExample {

    public static int sum(){

        return StudentDataBase.getAllStudents()
                .stream()
                .collect(summingInt(Student::getNoteBooks));
    }

    public static double average(){

        return StudentDataBase.getAllStudents()
                .stream()
                .collect(averagingInt(Student::getNoteBooks));
    }

    public static void main(String[] args) {

        System.out.println("Total No of notebooks : " + sum());

        System.out.println("Average No of notebooks : " + average());
    }
}
