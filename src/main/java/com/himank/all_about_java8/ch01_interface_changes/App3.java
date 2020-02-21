package com.himank.all_about_java8.ch01_interface_changes;

public class App3 implements E, F {
    @Override
    public void doSth() {
     //   System.out.println("my implentation");
       // F.super.doSth();
        E.super.doSth();

    }

    public static void main(String[] args) {
        new App3().doSth();

    }
}

interface E {
    default void doSth() {
        System.out.println("inside E");
    }
}

interface F {
    default void doSth() {
        System.out.println("inside F");
    }
}