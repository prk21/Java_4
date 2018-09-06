package project4;


import java.io.*;
import java.util.*;

/**
 * StackSort is a program that will use two stacks to sort an array of integer values.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class StackSort {

    public static void main(String args[]) {

        int data[] = null;
        int result[] = null;

        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program sorts an array of integer values.");


        // Create an empty array of integers
        data = createArray(0, 1, 1);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with one integer
        data = createArray(1, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with two integers
        data = createArray(2, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 10 integers
        data = createArray(10, 0, 9999);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 20 integers
        data = createArray(20, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        System.out.println("Please enter the number of values to sort");
        int size = getInt("   It should be an integer value greater than or equal to 1.");
        // Create an array of the given size

        data = createArray(size, 0, 99);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();
        
        /*
         STEPS 1 TO 5
        
       VectorStack<Integer> lowerValues= new VectorStack<Integer> (size);
       VectorStack<Integer> upperValues= new VectorStack<Integer> (size);
       for(int i=0;i<size;i++)
    	   upperValues.push(data[i]);
       //TESTING : for(int i=0;i<size;i++)
       //             System.out.println(upperValues.peek());
    	  for(int i=0;i<size;i++)
       	   {result[i]=upperValues.pop();
       	            // Testing: System.out.println(result[i]);
       	   }
       	  //Testing : for(int i=0;i<size;i++)
    	  //  	         System.out.print(result[i]+" ");   
       
          */
    }


    /**
     * Use two stacks to sort the data in an array
     *
     * @param data An array of integer values to be sorted.
     * @return     An array of sorted integers. 
     */
    private static int[] doStackSort(int data[]) {

    int result[] = new int[data.length];

        
    // ADD CODE HERE TO SORT THE ARRAY USING TWO STACKS
    //Creating two stacks
    VectorStack<Integer> lowerValues=new VectorStack<Integer>(data.length);
    VectorStack<Integer> upperValues=new VectorStack<Integer>(data.length);
    
    int len=data.length;
    //Calculating length to run loop
    
    for(int i=0;i<len;i++)
    {
    	//if 1st value then add to left stack without any comparisions
    	if(i==0)
    		lowerValues.push(data[i]);
    	else if( data[i]>=lowerValues.peek()) 
         {	//if value of item  is greater than that on top of the left stack then
    		
    		//if right stack is empty then add value to the left stack
              if(upperValues.isEmpty())
        	     lowerValues.push(data[i]);
              else
              {
            	  //if right stack is not empty then
            	  
            	  //check if current data item is smaller than that on the top of right stack
            	  //if yes then push item on the left stack
            	  if(data[i]<upperValues.peek())
            		  lowerValues.push(data[i]);
            	  else if(data[i]>upperValues.peek())
            	  { 
            		  //if data item is greater than that on top of right stack then
            		  //run a loop to move all values smaller than the current data item onto the left stack 
            		  while( !upperValues.isEmpty())
              		   {
            			  if(data[i]>upperValues.peek())
            			  {
              		           int a= upperValues.pop();
              		           lowerValues.push(a);
              		      }
            			  else
            				  break;
              		   }
            		  //push the current data item on the top of the right stack
              		upperValues.push(data[i]);
            	  }
              }
         
         
         }
    	else if( data[i]<lowerValues.peek())
    	{
    		//if value is smaller than the top of the left stack
    		//then run a loop and shift the data items greater than the current data item onto the right stack
    		while(!lowerValues.isEmpty())
    		{
    			if(data[i]<lowerValues.peek())
    			{
    			         int a= lowerValues.pop();
    			         upperValues.push(a);
    		    }
    			else
    				break;
    		}
    		//push the data item on the top of the left stack
    		lowerValues.push(data[i]);
    	}
    }
    
    
    //To store the sorted array found from both stacks
    //into the result array
    while(!lowerValues.isEmpty())
    {
    	//move all values of the left stack onto the rigt stack
      int a=lowerValues.pop();
      upperValues.push(a);  
    }
    //now right stack contains all values in increasing order
    int i=0;
    //run aloop till the right stack becomes empty
    while(!upperValues.isEmpty())
    {
    	//one by one pop the items from the right stack
    	//and fill the values of the resultant array i.e. result
    	result[i]=upperValues.pop();
    	//while incrementing i, the index
    	i++;
    }
    
    //returning the resultant arry
    return result;

    }

    /**
     * Load an array with data values
     *
     * @param size The number of data values to generate and place in the array.
     * @param min The minimum value to generate.
     * @param max The maximum value to generate.
     * @return     An array of randomly generated integers. 
     */
    private static int[] createArray(int size, int min, int max) {

        Random generator = new Random();

        // If we get a negative size, just make the size 1
        if (size < 0) {
            size = 1;
        }
        // We need max > min for the random number generator to be happy

        if (max <= min) {
            max = min + 1;
        }

        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = min + generator.nextInt(max - min);
        }
      // Returning Data
        return data;
    }

    /**
     * Create a string with the data values from an array
     *
     * @param data An array of integer values.
     * @return A string representation of the array.
     */
    private static String representationOfArray(int data[]) {
        String result = new String("< ");
        for (int i = 0; i < data.length; i++) {
            result += data[i] + " ";
        }
        result += ">";

        return result;
    }

    /**
     * Get an integer value
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt) {
        Scanner input;
        int result = 10;        //default value is 10
        try {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }
}
