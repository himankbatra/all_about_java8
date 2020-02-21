package com.himank.all_about_java8.java8Changes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {
	
	public static void main(String...strings )  {
		ArrayList<Integer> myList = new ArrayList<Integer>();
		for(int i = 0; i<10;i++)
			myList.add(i);
		
		List<String> studentList  = Arrays.asList("Akash","Kashif","Sushant","Hemant","Pankaj");
		
		
		System.out.println("\n/*-----------------  Stream Filteration / Mapping (Conversion) ----------------------------------- */\n");
		
		System.out.println(myList.stream()                             // Step 1 -> Applying Stream
				                 .filter(i->i%2==0)                    // Step 2 -> Applying Filtering Logic returning true/false for the particular item or 
				                 .map(i -> i+10)                       //           Applying Mapper which converts the value into the new value as per logic
				                 .count());                            // Step 3 -> Applying Aggregation Function
		
		System.out.println(studentList.stream()
				                      .filter(student -> student.equalsIgnoreCase("Kabir"))
				                      .findAny().orElse("NotFound"));
		
		
		
		System.out.println("\n/* ---------------- Stream Iteration -------------------------------------  */\n");
		
		myList.stream().limit(5)
		      //.skip(5)                                        // Step 1 -> Gives a Stream and applying restriction by passing index : n
		      .forEach(System.out::println);                    // Step 2 -> Applying For Each for iterating. 
		                                                        // => Limit is used for returning the stream upto the specified index i.e n-1.
		                                                        // => Skip is reverse and returns the stream by skipping the items from starting i.e n.     
		studentList.stream()
                   .filter(student -> !student.equalsIgnoreCase("Kabir"))
                   .forEach(System.out::println);
		
		
		System.out.println("\n/*  ----------------- Stream to Collection -------------------------------  */\n");
		
		List<String> studentListNew = studentList.stream()
				                         .filter(student -> student.equalsIgnoreCase("Sushant") || student.equalsIgnoreCase("Akash"))
				                         .collect(Collectors.toList());
		
		studentListNew.forEach(System.out::println);
		
		List<String> integerIntoString = myList.stream().map(i -> i.toString()).collect(Collectors.toList());
		
		integerIntoString.forEach(System.out::println);
		
		
		System.out.println("\n/*  ----------------- Stream For Sorting -------------------------------  */\n");
		
		/*
		 * For Sorting of stream, element should be comparable otherwise it will throw a ClassCastException.
		 */
		
		List<String> sortedStudentList = studentList.stream().sorted().collect(Collectors.toList()); // You can also use a Comparator in sorted().
		sortedStudentList.forEach(System.out::println);
		System.out.println("----------------------------");
		
		
		
		HashSet<String> hm = new HashSet<String>(sortedStudentList);
		hm.forEach(System.out::println);
		System.out.println("----------------------------");
		

		Set<String> sortedStudentSet = studentList.stream().sorted().collect(Collectors.toSet());
		sortedStudentSet.forEach(System.out::println);

		//TreeSet<String> sortedStudentSet = (TreeSet<String>) studentList.stream().sorted().collect(Collectors.toSet());    -- ClassCastExcepiton		
		TreeSet<String> sortedStudentTreeSet = (TreeSet<String>) studentList.stream().sorted().collect(Collectors.toCollection(TreeSet::new));
		System.out.println(sortedStudentTreeSet);
		
		/*
		 * If you will notice the output of both above Collections is same which is an unsorted one even after using sorted
		 * stream in the second case, that's because Collectors.toSet() is an unordered Collectors and internally uses a HashSet.    
		 * if you try to cast the Collected Stream of Set into TreeSet, you will get a ClassCastException as shown below. 
		 * Moreover, if you want to use a Sorted Stream, use Collectors.toList() which uses an ArrayList or
		 * you can use Collectors.toCollection(TreeSet::new) which is an implementation of Sorted set as in Case 3.
		*/
		
		System.out.println("\n/*  ----------------- Stream For Reduction -------------------------------  */\n");
		
		/*
		 * Reduction is used to calculate aggregated results based on the whole stream like  
		 * sum of all the elements of a List, finding out the largest number through out a Set, count and many more.
		 * The name Reduction is given because the whole list is reduced into a single value only.
		 * 
		 * .reduce(agr1,BinaryOperator<T> accumulator) applies on the stream where
		 *         arg1 => is the output of the previous calculations or the first element of the stream
		 *         BinaryOperato<T> accumulator is the current element of the stream.
		 */
		
		 Optional<String> longestStudentName = studentList.stream().reduce(new BinaryOperator<String>() {
			
			@Override
			public String apply(String t, String u) {
				return t.length()>u.length()?t:u;
			}
		 });
		 
		 System.out.println("Longest Student Name By Reduce : " + longestStudentName.get());
		
		 Optional<String> allStudentName = studentList.stream().reduce((student1,student2) -> student1+" "+student2);
		 System.out.println(allStudentName.get());
		 
		 // max() & min() are special cases of reducing wherein max will preserve value with +1 and min will preserve the value returning -1.
		 Optional<String> smallestStudentNameByMinStream = studentList.stream()
				                                          .min((student1,student2) ->  {   
		                                                	 if(student1.length()>student2.length()) 
		                                                		 return 1;
                                                             else 
                                                            	 return -1; 
		                                                 });
		 
		 System.out.println("Smallest Student Name by Min : "+smallestStudentNameByMinStream.get());
		 
		 // Similarly, count is also a special case of stream where it counts no. of elements like mapToLong(e -> 1L).sum()
		 
		 System.out.println("\n/*  ----------------- Stream For Collection Flattening(Merge) ------------------------  */\n");
		 
		 /*
		  * Flattening of Collection means merging multiple collections into a single collection.
		  * As all the stream methods works on single collection or stream of collection. We need a special method which works on
		  * multiple collections. i.e flatMap() and flatMaptoDouble kind of methods (for particular data type).
		  * 
		  * This is the most important difference between map() and flatMap() that flatMap() works on Stream of multiple streams or collections
		  */
		 
		 List<String>       friendsList  = Arrays.asList("Swadheen","Prateek","Rajat","Inderjeet","Ashutosh"); 
		 List<List<String>> combinedList = Arrays.asList(studentList,friendsList);
		 
		 List<String> combinedSortedList = combinedList.stream()
				                                       .flatMap(list -> list.stream())
				                                       .sorted((item1,item2) -> item1.length()-item2.length())
				                                       .collect(Collectors.toList());
		 combinedSortedList.forEach(System.out::println); 
		 
		 
		 System.out.println("\n/*  ----------------- Stream For Collection for Grouping ------------------------  */\n");
		 
		 Map<Integer, List<String>> groupedMap = friendsList.stream().collect(Collectors.groupingBy(entity -> entity.length()));
		 groupedMap.forEach((key,value) -> System.out.println(key+"\t"+value));
		 
		 System.out.println("\n/*  ----------------- Stream Close Handler ------------------------  */\n");
		 
		 /*
		  * Stream close handler are the Runnable which gets fired when we close the Stream by stream.close() method.
		  */
		 
		 Stream<String> closable = combinedSortedList.stream().onClose(() -> System.out.println("Stream on Combined Sorted List is closing"));
		 System.out.println("Stream on Combined Sorted List is going to close.");
		 closable.close();
		 System.out.println("Stream on Combined Sorted List is closed.");
		
		 System.out.println("\n/*  ----------------- Further More Methods-------------------------------  */\n");
		 
		 /*
		  * distinct()             => gives a stream of unique elements by comparing it with object,equals(Object) method.
		  * allMatch() /anyMatch() => returns true if all/any element of collection satisfies the specified Predicate method.
		  * peek()                 => gives a stream on the same underlying stream, just it performs some additional functions on the elements as specified.
		  * forEachOrdered()       => same as forEach() method just it ensures the order of the element for parallel streams as well.
		  * parallel()             => divides the stream into chunck using Fork-Join Pool to decrease the response time. 
		  *                           -> should be used if the data is huge enough to apply parallel processing like 5000 records in list.
		  *                           -> should be applied on a datastructure that can be splitted easily like ArrayList,Tree can be splitted but LinkedList cannot.
		  *                           -> should be used with unordered() to indicate that the data structure doesn't maintain an order to increase the latency. 
		  *                           -> should be applied if the type of work can be splitted as well, like it should not create any other object in the pipeline
		  *                              like merging two hashMaps etc. as for Fork-Join Pool, it will create a new HashMap each time it will do a Fork & Join.
		  */
		 
	}
}
