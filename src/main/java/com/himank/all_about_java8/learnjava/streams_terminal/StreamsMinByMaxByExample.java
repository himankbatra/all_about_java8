package com.himank.all_about_java8.learnjava.streams_terminal;

import com.himank.all_about_java8.learnjava.data.Student;
import com.himank.all_about_java8.learnjava.data.StudentDataBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamsMinByMaxByExample {


    public static Optional<Student> minBy() {

        Optional<Student> studentOptional = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));

        return studentOptional;
    }

    public static Optional<Student> maxBy() {

        Optional<Student> studentOptional = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));

        return studentOptional;
    }

    public static List<Student> maxByMultipleStudents() {

        List<Student> maxStudents = new ArrayList<>();
        Optional<Student> studentOptional = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));

        Student maxStudent = studentOptional.isPresent() ? studentOptional.get() : null;
        if (maxStudent != null) {

            maxStudents = StudentDataBase.getAllStudents().stream()
                    .filter(student -> maxStudent.getGpa() == student.getGpa())
                    .collect(toList());

            System.out.println("Max Students are : " + maxStudents);
        }
        return maxStudents;
    }


    public static void main(String[] args) {

        System.out.println(minBy());

        System.out.println(maxBy());

        System.out.println(maxByMultipleStudents());
    }
}
