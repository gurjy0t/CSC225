import java.util.Arrays;

public class CountingSort{
	
	public static int[] CountingSort(int[] A){
		

		int len = A.length;
		int[] counting = new int[len];

		for (int i = 0;i < len;i++ ) {
			counting[A[i]]++;
		}

		int[] output = new int[len];

		int i = 0;
		while(i<len){

		}
		return counting;
	}

	public static void main(String[] args) {
		
		int[] array = {0,1,2,2,3};

		for (int i=0;i<array.length; i++ ) {
			System.out.print(array[i]);
		}
		System.out.println();
		int[] output = CountingSort(array);

		for (int i=0;i<output.length; i++ ) {
			System.out.print(output[i]);	
		}
		System.out.println();
	}

}