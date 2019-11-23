package com.himank.all_about_java8.ch01_interface_changes;

public class App1 implements A {
    @Override
    public void doSth() {
        System.out.println("inside App1");
    }



    public static void main(String[] args) {
        new App1().doSth();
        A.print();
    }
}

interface A {
    default void doSth() {
        System.out.println("inside A");
    }

    static void print()
    {
        System.out.println("i am static");
    }
}
