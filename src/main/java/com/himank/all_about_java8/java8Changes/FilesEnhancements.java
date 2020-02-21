package com.himank.all_about_java8.java8Changes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FilesEnhancements {

	public static void main(String[] args) {
		
		String fileLocation  = "C:\\Users\\1200592\\Downloads\\java8Changes";
				//"E:\\WrokSpace\\JunitDemo";
			
		/*
		 * Files.walk     => returns a Stream of all the paths that are can be walked down the heirarichaly and can be used in a pipeline for further processing.
		 * Files.find     => returns a Stream of all the paths that are within the specified maxDepth from the given heirarchy satisfying the BiPredicate function.
		 * Files.list     => returns a Stream of all paths that are found within the given directory.   
		 * Files.lines    => returns a Stream of strings constituting of all the lines that are present in the file.
		 * 
		 * BufferedReader.lines => works same as Files.lines just that it is used for reading large volume of files so that the file is read buffer by buffer.
		 * Files.walkTree       => works same as Files.walk  just that it also provide addtional features like 
		 *                         1.changing permissions, 
		 *                         2.listening events like preVisitDirectory(),postVisitDirectory(),visitFile(),visitFileFailed()
		 *                         3.can terminate when the particular file is found,skip the remaining subtree.
		 *                         
		 * it takes two arguements Files.walk(path,FileVisitor), FileVisitor is an interface containing the above 4 methods, if you don't want to implement
		 * the interface then you can also extend SimpleFileVisitor class.Each method of the class returns FileVisitResult object.                             
		 * 
		 */
		
		try(Stream<Path> paths = Files.walk(Paths.get(fileLocation))) {
			paths.map(path -> new File(path.toUri()))
			.filter(file -> !file.isHidden())
			.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int maxDepth = 2;        /* maxDepth is the number folders that it had to check considering the current fileLocation is at depth 1, 
		                            So, maxDepth of 2 means it would check in the current directory and 1 directory down heirarichaly. 
		                         */             
		
		try(Stream<Path> paths = Files.find(Paths.get(fileLocation),maxDepth,(path,basicFileAttribute) -> path.toString().endsWith("class"))) {
			paths.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}


/*
   Output :- 
  
   Files.list : 
                                E:\WrokSpace\JunitDemo\.classpath
								E:\WrokSpace\JunitDemo\.project
								E:\WrokSpace\JunitDemo\.settings
								E:\WrokSpace\JunitDemo\bin
								E:\WrokSpace\JunitDemo\src
  
   Files.walk : 
  
     							E:\WrokSpace\JunitDemo
								E:\WrokSpace\JunitDemo\.classpath
								E:\WrokSpace\JunitDemo\.project
								E:\WrokSpace\JunitDemo\.settings
								E:\WrokSpace\JunitDemo\.settings\org.eclipse.jdt.core.prefs
								E:\WrokSpace\JunitDemo\bin
								E:\WrokSpace\JunitDemo\bin\SimpleFactorial.class
								E:\WrokSpace\JunitDemo\bin\TestClass.class
								E:\WrokSpace\JunitDemo\bin\TestRunnerClass.class
								E:\WrokSpace\JunitDemo\src
								E:\WrokSpace\JunitDemo\src\SimpleFactorial.java
								E:\WrokSpace\JunitDemo\src\TestClass.java
								E:\WrokSpace\JunitDemo\src\TestRunnerClass.java
								
 Files.find : 
    
                                E:\WrokSpace\JunitDemo\bin\SimpleFactorial.class
								E:\WrokSpace\JunitDemo\bin\TestClass.class
								E:\WrokSpace\JunitDemo\bin\TestRunnerClass.class
                      								
								
 */
