package com.himank.all_about_java8.learnjava.functionalInterfaces;

import com.himank.all_about_java8.learnjava.data.Student;
import com.himank.all_about_java8.learnjava.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BiFunctionExample {

    static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> biFunction = ((students, studentPredicate) -> {

        Map<String, Double> studentGradeMap = new HashMap<>();
        students.forEach(student -> {
            if(studentPredicate.test(student)){
                studentGradeMap.put(student.getName(),student.getGpa());
            }
        });
        return  studentGradeMap;
    });

    public static void main(String[] args) {

        System.out.println( biFunction.apply(StudentDataBase.getAllStudents(),PredicateStudentExample.p2));
    }
}
