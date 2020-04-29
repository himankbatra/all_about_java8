package com.himank.all_about_java8.learnjava.methodreferences;

import com.himank.all_about_java8.learnjava.data.Student;

import java.util.function.Supplier;

public class SupplierMethodReferenceExample {

    Supplier<Student> studentSupplier = Student::new;

    public static void main(String[] args) {

        System.out.println();

    }
}
