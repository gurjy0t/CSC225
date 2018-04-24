/* PairSum.java
   CSC 225 - Summer 2016

   Template for the PairSum225 problem, which takes an array A and returns
	- true if there are two elements of A which add to 225
	- false otherwise

   The provided code for the problem (in the PairSum225 function below) implements
   a O( n*log(n) ) algorithm based on merge sort.

   Fang Dong - 09/06/2014
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class TrippleSum225 {

	/* PairSum225()
	The input array A will contain non-negative integers. The function
	will search the array A for a pair of elements which sum to 225.
	If such a pair is found, return true. Otherwise, return false.
	The function may modify the array A.
	Do not change the function signature (name/parameters).
	*/

	public static boolean TrippleSum225(int[] A){

		int n = A.length;

		int arr[] = new int[226];
			
		

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


	/*  MergeSort()
	 *  MergeSort function will sort the numbers in array A with index from le to ri.
	 *  The time complexity of this function is O(nlogn).
	 */

	public static void MergeSort(int[] A, int le, int ri){

		if (ri<=le) return;

		int mid=le+(ri-le)/2;


		/*MergeSort the left and right parts respectively,
		 * one from le to mid, another from mid+1 to ri*/
		MergeSort( A, le, mid);
		MergeSort( A, mid+1, ri);

		/*Merge two parts together as sorted one part*/
		Merge(A, le, mid, ri);

		/*To enable the assertion function, the program should be run with
		 * -ea option. e.g. java -ea PairSum_nlogn HasPair_10.txt */
		assert isSorted(A, le, ri): "Sorting algorithm fails";
	}

	/*  Merge()
	 *  Merge() function merges two sorted sub-arrays to a sorted unit. The two
	 *  sub-arrays are numbers of A with index from le to mid, and from mid+1 to
	 *  ri respectively.
	 */
	public static void Merge(int[] A, int le, int mid, int ri){



     	/*Define an auxiliary array*/
		int[] A_aux=new int[ri-le+1];

		/*Merge two parts into the auxiliary array*/

		int p1 = le;
		int p2 = mid+1;

		for(int i=0; i<A_aux.length; i++) {
			if(p1 > mid) {
				A_aux[i] = A[p2];
				p2++;
			}
			else if(p2 > ri) {
				A_aux[i] = A[p1];
				p1++;
			}
			else if(A[p1] <= A[p2]) {
				A_aux[i] = A[p1];
				p1++;
			}
			else {
				A_aux[i] = A[p2];
				p2++;
			}
		}


		/*Copy the sorted elements from auxiliary array to original array*/

		for(int i=0; i<A_aux.length; i++) {
			A[le+i] = A_aux[i];
		}


		/*Delete the auxiliary array*/
		A_aux=null;
	}

	/* isSorted()
	 Check whether or not the given array is successfully sorted.
	 If it is, return true; otherwise return false.
	*/
    public static boolean isSorted(int[] A, int le, int ri){
    	for (int i= le+1;i<=ri;i++)
    		if (A[i]<A[i-1]) return false;
    	return true;
    }


	/* main()
	 Contains code to test the TrippleSum225 function.
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

		boolean pairExists = TrippleSum225(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s a pair of values which add to 225.\n",pairExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}

}
