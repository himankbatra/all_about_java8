package com.himank.all_about_java8.java8Changes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;


/*
 * Accumulaotrs are the newly added concept in Java8 which works by passing one accumulator function and one base value.
 * Accumulator function accumulates the result from x & y and returns the result in x, y is the new value from the collection or stream.
 * Basically, it works like reducing the whole stream to a particular value by using Compare&Swap algorithm.
 * 
 * There are two Accumulators : LongAccumulator & DecimalAccumulator intorduced in Java8
 * 
 */

public class AccumulatorsDemo {
	
	public static void main(String[] args) {
		
		LongBinaryOperator accumulatorFunction = (x,y) -> x+y;
		LongAccumulator accumulator = new LongAccumulator(accumulatorFunction, 0l);
		ExecutorService service = Executors.newFixedThreadPool(200);
		
		IntStream.range(0, 100).forEach(i -> service.submit(() -> accumulator.accumulate(i)));
		try {Thread.sleep(200);}catch(InterruptedException e) {}
		
	}

}
