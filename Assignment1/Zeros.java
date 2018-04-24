import java.util.Arrays;
import java.util.Scanner;

public class Zeros{
	public static int Zeros(int DecimalNumber){
		int counter = 0;
		int altCounter = 0;
		int variable = DecimalNumber;
		while(variable > 0){
			if (variable%2 == 0) {
				counter++;
				variable = (variable/2);
			}
			else if(variable%2 == 1) {
				if(counter > altCounter){
					altCounter = counter;
				}
				counter = 0;
				variable = (variable/2);
			//break;
			}
		}
		return altCounter;
	}

	public static void main(String[] args) {
	
	int input = 0;
	Scanner in = new Scanner(System.in);
	System.out.println("Please enter a non-negataive integer, greater than 0");
		

	//while(!in.hasNextInt()){
		input = in.nextInt();
		System.out.println("This is the longest number of repeating zeros in the binary representation of your numer: ");
		System.out.println(Zeros(input));
		//if (in.nextLine().equals("")) {
		//break;
		
		
	//}
	
		
		
	
	//System.out.println("Hello");
	//int output = Zeros(input);
	//System.out.println(output);

}
}

