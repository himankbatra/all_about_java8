package com.himank.all_about_java8.java8Changes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/*
 * LongAdder is introduced in Java8 in place of AtomicLong and AtomicInteger, Unlike Atomic data members which work on Concurrent mechanism of allowing multiple threads to read 
 * the data simultaneously & for update it uses Compare&Swap Algorithm for maintaining atomicity.
 * 
 * In Compare&Swap Algorithm, we increment the value what we got in hand and compare it by the current value.
 * if it comes appropriate then it is change the value and return true, else if the values are not appropriate meaning that any other thread has changed the value and returns false
 * then it does the whole process again in an infinite loop.
 * 
				   public final long incrementAndGet() {
				    for (;;) {
				        long current = get();
				        long next = current + 1;
				        if (compareAndSet(current, next))
				          return next;
				    }
				   }
				   
				   In JDK8, this single line is JVM dependent and is translated into optimized JIT instructions for different-different platforms.
				   
					public final long incrementAndGet() {
					    return unsafe.getAndAddLong(this, valueOffset, 1L) + 1L;
					}		
					
	       					 
 * This works better than volatile + synchronized as it does not involve locking mechanism but for large number of threads this infinite comparing and swap costs more 
 * than the locking mechanism.  
 * Fortunately, In Java8 the LongAdder are introduced which works on principle of Parallelism. 
 * It maintains a long array internally and each segment of that array is dedicated to a single thread, each thread increase their own segment value without using any lock which  
 * reduces the time taken. At last we just call the method sum() which adds up all the values of the array at that point of time and gives us the result. 
 * 
 * 
 */

public class LongAdderDemo {

	public static void main(String[] args) {
		
		LongAdder counter = new LongAdder();
		ExecutorService service = Executors.newFixedThreadPool(400);
		Runnable incrementAction = () ->  counter.increment();
		
		for(int i=0;i<400;i++)
		  service.execute(incrementAction);
		
		try {Thread.sleep(200);}catch(InterruptedException e) {}
		Long l = counter.sumThenReset();
		System.out.println(l);
		service.shutdown();

	}

}


/*
 *  Output => 400
 */