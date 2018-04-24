/**
* This program computes the total number of inversions in an 
* array of n integers. By definition of the problem, the number 
* of inversions is at max n.
* @author Gurjyot Grewal
* ID: V00820022
* June 9, 2016 
*
* SOME CODE FOR THE MAIN METHOD OF THIS CLASS HAS BEEN OBTAINED FROM ASSIGNMENT 1 AND CORRESPONDING LABS
*/	

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;	

public class CountInversions {
	
	public static int CountInversions(int[] A){
  		 
        int temp = 0;
        int inv = 0;
        int curr = 0;
        int len = A.length;
        for(int i = 1; i < len; i++) {
            curr = i;
            
            while(curr>0 && A[curr-1]>A[curr]){
            	inv++;
            	temp = A[curr];
            	A[curr]= A[curr-1];
            	A[curr-1] = temp;
            	curr--;
            }
        }
        return inv;
	}

	public static void main(String[] args) {
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		long pairExists = CountInversions(array);
		System.out.println("There are " + pairExists + " inversions in the array");
		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}
}	