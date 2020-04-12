package com.himank.all_about_java8.learnjava.constructorreference;

import com.himank.all_about_java8.learnjava.data.Student;

import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferenceExample {

    static Supplier<Student> studentSupplier = Student::new;

    static Function<String,Student> studentFunction = Student::new;

    public static void main(String[] args) {

        System.out.println(studentSupplier.get());

        System.out.println(studentFunction.apply("ABC"));

    }
}
