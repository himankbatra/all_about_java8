package com.himank.all_about_java8.learnjava.parallelstream;

public class Sum {

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void performSum(int input){
        total+=input;
    }
}
