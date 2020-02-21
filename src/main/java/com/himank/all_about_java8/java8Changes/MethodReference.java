package com.himank.all_about_java8.java8Changes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class Person  {
	
	private String personId;
	private String personName;
	private int salary;
	public static HashMap<String,String> methodAttribute =  new HashMap<>();
	public static final String PERSON_ID             = "PersonId";
	public static final String GETTER_PERSONID       = "getPersonId";
	public static final String PERSON_NAME           = "PersonName";
	public static final String GETTER_PERSONNAME     = "getPersonName";
	public static final String PERSON_SALARY         = "Salary";
	public static final String GETTER_PERSONSALARY   = "getSalary";

	public Person() {
		super();
	}
	
	static {
		methodAttribute.put(PERSON_ID,GETTER_PERSONID);
		methodAttribute.put(PERSON_NAME,GETTER_PERSONNAME);
		methodAttribute.put(PERSON_SALARY,GETTER_PERSONSALARY);
	}
	
	
	public Person(String personId, String personName,int salary) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.salary = salary;
	}

	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public HashMap<String, String> getMethodAttribute() {
		return methodAttribute;
	}

	public void setMethodAttribute(HashMap<String, String> methodAttribute) {
		Person.methodAttribute = methodAttribute;
	}
	
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personName=" + personName + "]";
	}
	
	public static int getPersonCount(){
		return 5;
	}
	
	public void raiseSalary()  {
		System.out.println(this.getPersonName()+" got Salary Hike of "+ this.salary/10);
		this.salary += this.salary/10;
	}
	
	public int getVowelCount()  {
   	 int count=0;
   	 String word = this.getPersonName();
   	 for(int i=0;i<word.length();i++)
   		 if(String.valueOf(word.charAt(i)).equalsIgnoreCase("A")   ||
   		    String.valueOf(word.charAt(i)).equalsIgnoreCase("E")   ||
   			String.valueOf(word.charAt(i)).equalsIgnoreCase("I")   ||
   			String.valueOf(word.charAt(i)).equalsIgnoreCase("O")   ||
   			String.valueOf(word.charAt(i)).equalsIgnoreCase("U")   )
   			 count++;	 
   	 return count;
   		 
    }
	
	/*
	 * Generic Program to run a predefined function for a given attribute on a List of objects Using Reflection.
	 * The particular function is applied on each object of the list one by one. 
	 */
     public static <T, R> HashMap<Person,T> performFunctionOnEachPerson(List<? extends Person> listPerson, String attribute, Function<R,T> function) {    	 
    	 HashMap<Person,T>  hm = new HashMap<>();
    	 Class<? extends Person> personClass = listPerson.get(0).getClass();
    	 Method value = null;
 		 try {
			value = personClass.getDeclaredMethod(methodAttribute.get(attribute));
    	    for(Person Person : listPerson) 
    		   hm.put(Person,function.apply((R)value.invoke(Person)));
 		  }catch (NoSuchMethodException  | SecurityException | IllegalAccessException | InvocationTargetException e) {
			     e.getMessage();
		}
    	 return hm;
     }
}

class InbuiltFunctions {

	public static Function<String,Integer> getVowelCount() {	
		Function<String,Integer> fn = new Function<String,Integer>() {
		public Integer apply(String word) {
			int count=0;
		   	 for(int i=0;i<word.length();i++) {  
		   		 char ch = word.charAt(i);
		   		 switch(ch) {  
		   		    case 'A' : case 'a' : case 'E' : case 'e' : case 'I' : case 'i' : case 'O' : case 'o' : case 'U' : case 'u' : 
		   			count++;  
		   			break;
		   		 }	 
		   	 }	 
			return count;
		}
    };  
      return fn;
  }  	
}

public class MethodReference {

	public static void main(String[] args) {
		
		ArrayList<Person> custList  = new ArrayList<Person>(5);
		custList.add(new Person("99","Hemant",1000));
		custList.add(new Person("107","Sushant",1000));
		custList.add(new Person("113","Pankaj",1400));
		custList.add(new Person("115","Akash",1400));
		custList.add(new Person("116","Kashif",2000));
		custList.add(new Person("119","Kabir",2000));
		
		custList.forEach(Person::raiseSalary);                                              // Method reference for a Non - static method.
		
		/*
		 *  For finding out no. of vowels in Person's Name
		 */
		
		System.out.println("Vowel Count of Person Names are till Java 7\n" );               //  Method reference for a Static method.
		ArrayList<Integer> al = new ArrayList<Integer>(custList.size());
		for(Person c : custList) 
            al.add(c.getVowelCount());							
		

		for(Integer i :al) 
			System.out.println(i);

		
		/*------------------------  Java 8 Changes for Programming Techniques  ------------------------------*/
		
		System.out.println("Vowel Count of Person Names are in Java 8\n" );               
		
		System.out.println("Through Stream");	//  Method reference for a Static method.
		custList.stream().map(Person::getVowelCount).collect(Collectors.toList()).forEach(System.out::println);
		
		
		System.out.println("With Function Anonymous Implementation");	
		HashMap<? extends Person, Integer> hm = Person.performFunctionOnEachPerson(custList,Person.PERSON_NAME,
        	new Function<String,Integer>() {
				public Integer apply(String word) {
					Integer count = new Integer(0);
				   	 for(int i=0;i<word.length();i++)
				   		 if(String.valueOf(word.charAt(i)).equalsIgnoreCase("A")   ||
				   		    String.valueOf(word.charAt(i)).equalsIgnoreCase("E")   ||
				   			String.valueOf(word.charAt(i)).equalsIgnoreCase("I")   ||
				   			String.valueOf(word.charAt(i)).equalsIgnoreCase("O")   ||
				   			String.valueOf(word.charAt(i)).equalsIgnoreCase("U")   )
				   			 count++;
					return count;
				}
			
		    });
		
		hm.forEach(new BiConsumer<Person, Integer>() {
			@Override
			public void accept(Person cust, Integer count) {
               System.out.println(cust.getPersonName()+" "+count);
			}
		});
		
		// Drawback : This is same as calling getVowelCount from Class but if you need only for one time, then anonymous Implementation is good.
		// Otherwise go for the Class level implementation as below.
		
		
		/* ----------------------- Function with class level Implementation ----------------------------*/
		System.out.println("With Function with class level Implementation");	
		Person.performFunctionOnEachPerson(custList,Person.PERSON_NAME,InbuiltFunctions.getVowelCount())
		.forEach(new BiConsumer<Person, Integer>() {
			@Override
			public void accept(Person cust, Integer count) {
               System.out.println(cust.getPersonName()+" "+count);
			}
		});	
		
		
		/* ----------------------- Map Interface Advancement ----------------------------*/
		
		System.out.println("----------------------Map Interface Advancement---------------------------------");
		
		HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
		 hashMap.put("Akash", 2000);                                                             // simple inserts
		 hashMap.putIfAbsent("Kashif", 10000);                                                   // put if not present, else replace 
		 hashMap.computeIfAbsent("Sushant", new Function<String, Integer>() {                    // passing function without Lambda Expression
		    @Override
		    public Integer apply(String t) {
		    	 if(t.equalsIgnoreCase("Sushant"))  return 3000;
		    	 else                               return 4000; 
		    }
		 });
		                    //or
		 hashMap.computeIfAbsent("Sushant", key -> 6000); 		                                  // if not present, compute and put it in hashMap. 
		 hashMap.computeIfPresent("Kabir" ,(key,value) -> 5000);                                  // computes if only present, doesn't put if absent.
		 hashMap.merge("Akash", 3000, (i1,i2) -> i1+i2);                                          // merge the value of the key as per the Bifunction 
		 
		 hashMap.forEach((key,value)-> System.out.println(key+" "+value));
		 
	}

}

