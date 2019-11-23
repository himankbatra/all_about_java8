package com.himank.all_about_java8.java8Changes;

public class Java8Notes {
	
	/*
	 * #1 forEach vs forEachOrdered vs forEachRemaining 
	 * 
	 * => forEach is in the iterable interface to iterate over a collection whereas 
	 * => forEachOrdered is in the stream api which is used to perform the given operation on the stream sequentially in the ordered way even if you have a parallel stream & 
	 * => forEachRemaining is in the Iterator interface and is same as forEach but the forEach method will work on the collection from starting but forEachRemainig will work
	 *    for the remaining element for the iterator.  
	 * 
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #2 SplitIterators
	 * 
	 * => With Java8  We have new Iterators that can be spiltted and we can perform the operation parallelly.   
	 * 
	 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #3 Collection API Improvements
	 * 
	 * => All the collection are lazy initialized in Java8 meaning thereby the Object will get the memory allocation when you insert the first element in the collection 
	 *    not on calling the constructor like in previous versions. 
	 * 
	 * => removeIf(Predicate filter) is used to filter out the elements that doesn't satisfies the given predicate returning true or false for the elements.
	 *    Predicate is a new Functional Interface having a method test() returning boolean for each and every element.
	 *    
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------   
	 * 
	 * #4  Map Improvements
	 * 
	 * => HashMap Optimization
	 *    HashMap is optimized in such a way that the LinkedList formed in the bucket for the same key is now converted into a TreeSet offering complexity of O(log(n)) from O(n).
	 *    In order to achieve this, there are 3 private static final fields 
	 *    TREEIFY_THRESHOLD    (8)  =>  minimum no. of nodes present in Linked List to convert into TreeSet (it converts as soon as you add, not at the time of rehashing).  
	 *    UNTREEIFY_THRESHOLD  (6)  =>  minimum no. of nodes present in Linked List to convert into LinkedList from a TreeSet(it happens when rehashing).
	 *    MIN_TREEIFY_CAPACITY (64) =>  minimum capacity of the HashMap to convert the LL into TreeSet.
	 *    
	 * => ConcurrentHashMap Optimization 
	 *    In ConcurrentHashMap ,firstly there used to be a Segment class extending ReetrantLock which offers concurrency on the segment.Each Segment has its own HashMap & even 
	 *    on Rehashing, the particular HashMap  of the segment is rehashed.In Java 8, Segment class is removed and the Lock is taken on the first element of the bucket of the 
	 *    HashMap.
	 *    
	 * => New Methods Like compute(), computeIfPresent(), merge(), replaceAll();
	 * 
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #5 Interface Improvements  
	 * 
	 * => Interfaces are also enhanced by introducing Functional Interfaces,default and static methods in Interfaces.  
	 * 
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #6 Lambda Expressions 
	 * 
	 * => The whole new Lambda expression concept is been introduced to utitlize the functional ineterface of Java8 for functional programming which is taken from Scala.
	 *    
	 * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------    
	 *    
	 * #7 Stream Api
	 * 
	 * => Stream Api is introduced in Java8 to provide dynamic data which is derived on demand with less code using LambdaExpressions.
	 *     
	 * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #8 Method Reference
	 * 
	 * => From Java 8 , method also get reference and we can use the method reference in functional programming. - Detailed in MethodReference.java.
	 *  
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------    
	 * 
	 * #9 Executor Framework Enhancement 
	 * 
	 * => WorkStealingPools  
	 *    A new type of thread pool i.e newWorkStealingPools() are also introduced in the Executor framework in which the idle threads steals the task of the executing threads 
	 *    so as to increase the performance. is internally based on ForkJoinPool but the difference is it can take the total no. of threads & can work on total no. of threads 
	 *    that are offered in the processor like Runtime.getRuntime().getAvailableProcessors().
	 *    
	 * => CompletableFuture   
	 *    Completable future is newly introduced in Java 8  which implements CompletionStage & Future Interface. Future Interface all we know is used to obtain the result from
	 *    a callable work but CompletionStage interface denotes a stage of the work from which the particular work can be done synchronously and asynchronously. 
	 *    It also represents a pipeline of stages which can be trigger processing of task after completion of the previous task 
	 *    with methods like .thenApply() , thenApplyAsyn() , thenAccept() and may more.
	 *  
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #10 Lazy Initialization of Logger
	 * 
	 * => java.util.logging.Logger is also lazily initialized in such a way that the methods like info(),finer(),finest() takes the argument of Supplier<T> supplier which  
	 *    is invoked at runtime. 
	 *    In previous versions if the message is given by any other method like logger.info(getMessage()) and your logging level is set on higher like SEVERE, even then it will
	 *    invoke the method without printing the message, but with now a Supplier function, it is decided at runtime that the method is to be called or not depending upon how
	 *    much time the method will take.
	 *    
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------   
	 * #11 LongAdder, LongAccumulator, DoubleAccumulator
	 * 
	 * => Class like LongAdder is been introduced for maintainig a parallel counter between multiple threads in place of AtomicInteger.
	 * => Class like LongAccumulator, DoubleAccumulator are introduced for accumulatig the result from a collection or stream of collection using Lambda Expressions.
	 * => Atomic Datamembers like AtomicInteger and AtomicLong are internally enhanced by implementing a new class "Unsafe" which is JVM dependent and optimize the
	 *    code for the underlying JIT.
	 *     
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #12 FilesEnhancements
	 * 
	 * => Methods like lines(), walk(), walkTree(), find(), list() in Files class and lines() in BufferedReader are newly introduced returning a stream which can be can  
	 *    be pipelined for further processing.   
	 *
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * 
	 * #13 Java Date-Time Api
	 * 
	 * => Java Date-Time is newly introduced as
	 *    1.Previously, there were Date class in java.util & java.sql package both and for storing the date in Db, we need to convert java.util.Date into java.sql.Date .
	 *    2.Date & Time is given by the same class & if we want only date or only time, you need to extract from it.
	 *    3.There were no zonal supports for different-different time zones as well.
	 *    4.DateFormatter abstractclass and SimpleDateFormatter classes are separate class used for parsing and formatting found in java.text.
	 *    5.All the date in the Java were all mutable, so there were no memory level optimization done for date in previous versions.
	 *    
	 *    Multiple classes have been intoroduced like LocalDate,LocalTime,LocalDateTime,Zone,ZoneId,ZoneRules,TemporalAdjusters and so on.
	 *    
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------    
	 */

}
