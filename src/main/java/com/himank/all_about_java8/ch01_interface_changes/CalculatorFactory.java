package com.himank.all_about_java8.ch01_interface_changes;

public abstract class CalculatorFactory {

    public static Calculator getInstance() {
        return new BasicCalculator();
    }
}
