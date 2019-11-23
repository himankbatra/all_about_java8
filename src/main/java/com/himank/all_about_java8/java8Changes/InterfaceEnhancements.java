package com.himank.all_about_java8.java8Changes;

/*  1st Enhancement   =>  Functional Interfaces
 *      Functional Interfaces are those Interfaces which has exactly one abstract method but can have multiple  default, static method.
 *      If the annotation @FunctionalInterface is applied on a Non-Functional Interface, the compiler will give CTE as the Interface is not a functional interface. 
 * 
 * 2nd Enhancement  =>   Default method in Interfaces
 *       Features   :-
 *           1. Public method defined in java.lang.Object cannot be overridden with same signature but Protected member can with default access specifier.
 *           2. Interface A can call default method of Interface B if A extends B.
 *           3. Need to override default method in case of multiple inheritance for both Interface and Classes. 
 *       Advantages :-
 *           1. The major reason to introduce default methods in interfaces is to support Lambda expressions in Collection API.
 *           2. There is no need of utility class now, as all the methods can be placed in interfaces from now onwards.
 *           3. It provides base implementation of the interface which is offered by Abstract Classes in previous versions.
 *   
 *   
 *  3rd Enhancement.  =>  Static method in Interfaces
 *       Features   :-
 *           1. Static method of Interfaces cannot override any method of Object class. 
 *           2. No Diamond Problem for Static methods if exists in multiple parent interfaces.
 *           3. You can declare same static method in class as well as in interfaces but that's not overridden. That are static method of class or interfaces only.
 *           4. Static method of Interfaces are visible to the particular Interfaces only. They can be accessed in the Interface or with the Interface name if called outside 
 *              the defined interface.They cannot be accessed by the implementing class or extending interface. 
 *       Advantages  :- 
 *            1. There is no need of utility class now, multiple methods can be placed in interfaces from now onwards.  
 */


@FunctionalInterface                                        
interface FunctinalInterface1
{
	public void functionalMethod();
	
	default void display(String s) {
		System.out.println("Interface1 :"+s);
                                // static method of interfaces are visible to interfaces only. Cannot be called by Implementing Class.
	}
}

interface Interface1
{
	default void display(String s) {
		System.out.println("Interface1 :"+s);
		print();                                    // static method of interfaces are visible to interfaces only. Cannot be called by Implementing Class.
	}
	
	default void finalize() throws Throwable {      //  -- protected method defined in java.lang.Object can be overridden. 
		System.out.println("Finalized method in Interface1 gets called");
	}           
	                          
	//   default boolean equals(Object s){ }        //  -- public method defined in java.lang.Object cannot be overridden.  
   
	 static void print()  {
		System.out.println("Interface1 prints  static method is called");
	}
}


interface Interface2  {
	default void display(String s) {
		System.out.println("Interface 2  :"+s); 
	}
	
	default void display2(String s) {
		System.out.println("Functional Interface :"+s);
		
	// Interface1.super.display("Not called");    // -- Allowed only if Intrface2 extends Interface1 
	}
	
	static void print()  {
		System.out.println("Interface1 prints  static method is called");
	}
	 
	//   static void finalize()  throws Throwable {}    // CTE : static method cannot override methods of Object class. 
	
}

interface Interface3 extends Interface1, Interface2   { 
	
	/*
	 * It is compulsory to override the default method of Interfaces to avoid Diamond problem (multiple inheritance) & if you are not performing 
	 * multiple inheritance then no need to override, inheritance will still hold for default methods defined in interfaces.
	 */
	@Override
	 default void display(String s) {                        
		System.out.println("Class method gets called");         
	    //    print();                                           --  CTE as static method of parent interfaces are not inherited, unlike default methods.	                                                                       
		// Interface1.super.display(s);                       // --  You can call the interface method directly provided inheritance should exist.
	}
	
	/*
	 * Even if print() method is defined in both the parent interfaces but still there is no Diamond Problem as there is no concept of inheritance
	 * for static methods
	 */
	
}



public class InterfaceEnhancements implements Interface3{

	 public void finalize() throws Throwable{
		System.out.println("Finally method of the class get called");
	 }  
	 
    public static void print()  {
      System.out.println("Static method of implementing class");   // You can declare a same method with same name, but its not an overridden method.
    }                                                              // Its class it's own method.

	public static void main(String[] args) {
		InterfaceEnhancements i = new InterfaceEnhancements();
		i.display(" is working.");
		// i.print();                                               will not work until you have declared a same method in the class as well because
		// or ((Interface1) i).print()                              object of Implementation class cannot call static method defined in interfaces. 
		Interface1.print();                                      // You can still call static method of interfaces from Interface name as like a java class.
		
	}
	
}

