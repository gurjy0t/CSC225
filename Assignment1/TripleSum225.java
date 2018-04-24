/* TripleSum.java
   CSC 225 - Fall 2016
	
*	The main method of this program was derived from the main method in PairSum() from CSC225
*   Written By : Fang Dong - 09/06/2014
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class TripleSum225 {

	/* TripleSum225()
	The input array A will contain non-negative integers. The function
	will search the array A for 3 elements which sum to 225.
	If such a trio is found, return true. Otherwise, return false.
	The function may modify the array A.
	*/

	public static boolean TripleSum225(int[] A){

		int n = A.length;

		int arr[] = new int[226];
			
		/*Methodology of array with 1's and 0's derived from counting sort. Counting sort explanation accessed at http://www.geeksforgeeks.org/counting-sort*/	
		for (int i = 0; i<n ; i++ ) {
			if (A[i] <226) {
				arr[A[i]] = 1;
			}
		}

		for (int i =0; i<225 ; i++ ) {
			for (int j=0;j<225 ;j++ ) {
				for (int k=0;k<225 ;k++ ) {
					if(arr[i] != 0 && arr[j]!=0 && arr[k]!=0){
						if((i + j + k)==225  || (3*i)==225 || (3*j)==225  || (3*k)==225 || (2*i+j)==225  ||(2*i+k)==225 || (2*j+i)==225 || (2*j+k)==225 || (2*k+i)==225 || (2*k+j)==225) {

							return true;
						}
					}
				}				
			}			
		}
		return false;
	}



	/* main()
	 Contains code to test the TripleSum225 function.
	*/
	public static void main(String[] args){
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

		boolean pairExists = TripleSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s a pair of values which add to 225.\n",pairExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}

}
