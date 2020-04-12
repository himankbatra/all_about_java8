package com.himank.all_about_java8.learnjava.defaults;

public interface Interface3 extends Interface2 {

    default void methodC(){

        System.out.println("Inside Method C");
    }
}
