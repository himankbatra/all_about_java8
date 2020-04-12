package com.himank.all_about_java8.learnjava.defaults;

public interface Interface1 {

    default void methodA(){

        System.out.println("Inside Method A" + Interface1.class);
    }
}
