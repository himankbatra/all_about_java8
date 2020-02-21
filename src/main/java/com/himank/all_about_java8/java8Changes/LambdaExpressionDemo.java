package com.himank.all_about_java8.java8Changes;

@FunctionalInterface   
interface FunctionalInterfaceDemo1
{
	String  operation(int a, int b);
}

@FunctionalInterface      
interface FunctionalInterfaceDemo2
{
	void display(String message);
}

public class LambdaExpressionDemo {

	public static void main(String[] args) {
		
		/* --------------  Old Style of Interface Implementation ----------------- */
		FunctionalInterfaceDemo1 fid1 = new FunctionalInterfaceDemo1()
		{
	        @Override
	        public String operation(int a,int b)
	        {
	        	return String.valueOf(a+b);
	        }
		};
		System.out.println("Result Using old style"+fid1.operation(2, 3));
		
		
		
		/* --------------  Lambda Expression Interface Implementation ----------------- */
		
		/*
		 * if returning in single line, can be written without returnType as well like this.
		 */
		
		FunctionalInterfaceDemo1 fid3   =  (int a, int b) -> {return String.valueOf(a+b);};
		                              /*or*/
		FunctionalInterfaceDemo1 fid2   =  (int a, int b) -> String.valueOf(a+b);
		                          
		System.out.println("Result using Lambda Expression "+fid3.operation(2, 3)+" "+fid2.operation(2, 3));
		
		/*
		 * if the argument of the method is just 1, no need to put it in the brackets.
		 */

		FunctionalInterfaceDemo2 fid4 = message -> System.out.println("Result is : "+message);
		fid4.display(fid3.operation(4, 6));
		
	}
	
}
