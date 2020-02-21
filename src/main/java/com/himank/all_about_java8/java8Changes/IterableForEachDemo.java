package com.himank.all_about_java8.java8Changes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
 * Arguments : takes object of any functional Interface, lambda expression, method reference. 
 * Working   : it internally maintains a simple forEach only, just it makes the code more concise. 
 */


public class IterableForEachDemo {

	public static void main(String[] args) {
		
           ArrayList<Employee> empList = new ArrayList<Employee>();		
           for(int i = 1 ; i<10;i++)
        	empList.add(new Employee(""+i,String.valueOf(i))); 
           
           /*--------- For Each with anonymous Implementation  ---------*/  
		   empList.forEach(new Consumer<Employee>() {
			@Override
		    public void accept(Employee t) {
				System.out.println(t.getId() +" "+t.getName());
			}
		   });
		   
		   /*--------- For Each with  Class Level Implementation  ---------*/
		   empList.forEach(new MyEmployeeConsumer());
		   
		   /*--------- For Each with  Lambda Implementation  ---------*/
		   empList.forEach(emp -> System.out.println(emp.getId()));
		   
		   /*--------- For Each with  Method Reference Implementation  ---------*/
		   empList.forEach(System.out::println);
		   
		   List<String> studentList = Arrays.asList("Akash","Kashif","Sushant","Hemant","Pankaj");
		   
		   
		   /*--------- For Each with  Effectively Final Concept ---------*/
		   
		   /* 
		    * int count=0;  ==>  Cannot use this count in the anonymous implementation of Consumer interface as it is outside the class definition  
		    *                    accept() method can only use the variables declared inside the implementation that too must be effective final at least. 
            */      
		   
		   studentList.forEach(new Consumer<String>() {
				int count=0;
				@Override
				public void accept(String t) {
				   System.out.println(count);
				}   
			});
		   
		   empList.forEach(Employee::toString);
	}
	
}

class MyEmployeeConsumer implements Consumer<Employee>{
 
	public void accept(Employee e)  {
		System.out.println(e.getId()+" saved in Database");
	}
}

class Employee
{
	private String id;
	private String name;
	
	public Employee() {}

	public Employee(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return this.getId()+" "+getName();
	}
	
}
