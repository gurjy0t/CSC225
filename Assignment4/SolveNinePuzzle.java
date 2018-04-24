/**
* CSC 225 Assignment 4
* @author Gurjyot Grewal
* ID: V00820022
* July 25, 2016 
* This program has 2 embedded classes; Board and Tree. 
*/  
import java.util.*;
import java.io.*;
import java.lang.Integer;

public class SolveNinePuzzle{

	/*Board objects to be used as nodes in the tree*/
	static class Board{
		int[][] boardState = new int[3][3];
		String stringRep;
		ArrayList<Board> children = new ArrayList<Board>();
		Board parent = null;

		public Board(int[][] boardState){
			this.boardState = boardState;
			this.stringRep = ((((Arrays.deepToString(boardState)).replace("[","")).replace("]", "")).replace(" ", "")).replace(",", "");
			
		}

		public void addChild(Board child){
			this.children.add(child);

		}
		
		public void setParent(Board parent){
			this.parent = parent;
		}



	}
	/*Tree made of board objects has two functions; insert() and isRoot()*/
	static class Tree{
		private Board root = null;

		public Tree(Board b){
			root = b;

		}

		public void insert(Board parent, Board child){

			parent.addChild(child);
			child.setParent(parent);
		}
		public boolean isRoot(Board board){

			return (board.parent == null);
		}

	}
	/*This helper function returns a deep copy of the input 3x3 array*/
	public static int[][] deepCopy(int[][] original) {
    if (original == null) {
        return null;
    }

    int[][] result = new int[3][3];
    for(int i=0;i<3;i++){
		result[i]=original[i].clone();
	}
    return result;
}
	/* This function uses BFS to find the shortest path, if one exists, to solve the 9 puzzle. This function uses at least a Queue, Hashset and Tree
	 * This function runs in O(n^2) time.     
	 */
	public static boolean SolveNinePuzzle(int[][] input){

		Board b = new Board(input);
		int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
		Board g = new Board(goal);
		LinkedList<Board> q = new LinkedList<Board>();
		HashSet<String> h = new HashSet<String>();
		

		Tree tree = new Tree(b);
		
		q.addFirst(b);
		h.add(b.stringRep);

		while(!q.isEmpty()){
			Board v = q.removeLast();

			if(v.stringRep.equals(g.stringRep)){
				LinkedList<String> output = new LinkedList<String>();
				while(!tree.isRoot(v)){

					output.push(v.stringRep.substring(0,3).replace(""," ") + "\n" + v.stringRep.substring(3,6).replace(""," ") + "\n" + v.stringRep.substring(6,9).replace(""," ") + "\n");
					
					v = v.parent;
				}
				output.push(v.stringRep.substring(0,3).replace(""," ") + "\n" + v.stringRep.substring(3,6).replace(""," ") + "\n" + v.stringRep.substring(6,9).replace(""," ") + "\n");

				while(output.peekLast() != null){
					System.out.println(output.pop());
				}

				return true;
			}
			
			int index = (v.stringRep.indexOf('0'));

			int left = (index%3)-1;
			int right = (index%3) + 1;
			int up = (index/3) - 1;
			int down = (index/3) + 1;

			if(left>=0 && left < v.boardState.length){
				int[][] copy =  deepCopy(v.boardState);
				int temp = copy[index/3][left];
				copy[index/3][left] = copy[index/3][index%3];
				copy[index/3][index%3] = temp;

				Board leftShift = new Board(copy);
				if(h.contains(leftShift.stringRep)==false){
					tree.insert(v,leftShift);
				}
			}
			if(right >= 0 && right < v.boardState.length){
				int[][] copy =  deepCopy(v.boardState);
				int temp = copy[index/3][right];
				copy[index/3][right] = copy[index/3][index%3];
				copy[index/3][index%3] = temp;

				Board rightShift = new Board(copy);
				if(h.contains(rightShift.stringRep)==false){
					tree.insert(v, rightShift);
				}
			}
			if(up >= 0 && up< v.boardState.length){
				int[][] copy =  deepCopy(v.boardState);
				int temp = copy[up][index%3];
				copy[up][index%3] = copy[index/3][index%3];
				copy[index/3][index%3] = temp;

				Board upShift = new Board(copy);
				if(h.contains(upShift.stringRep)==false){
					tree.insert(v, upShift);
				}
			}
			if(down >= 0 && down< v.boardState.length){
				int[][] copy =  deepCopy(v.boardState);
				int temp = copy[down][index%3];
				copy[down][index%3] = copy[index/3][index%3];
				copy[index/3][index%3] = temp;

				Board downShift = new Board(copy);
				if(h.contains(downShift.stringRep)==false){
					tree.insert(v, downShift);
				}
			}


			for(int i =0; i<v.children.size(); i++){
				if(h.contains((v.children.get(i)).stringRep)==false){
					h.add((v.children.get(i)).stringRep);
					q.addFirst(v.children.get(i));
				}
			}
		}
		return false;
	}
	/*The main function takes a file with n 3x3 boards. Each line having 3 integers and boards being seperated by a new line*/
	public static void main(String[] args) throws Exception {
		

		if(args.length>0){
			double averageTime = 0.0;
			File inFile = new File(args[0]);

			Scanner in = new Scanner(inFile);
			int j = 0;
			int k =0;
			int[][] input = new int[3][3];
			while(in.hasNextInt() && k<9){
				
				for(int i =0; i<3; i++){
					input[j%3][i] = in.nextInt();
					k++;
					
				}
				j++;
				
				if(k%9==0){
					System.out.println("Reading board: " + ((j/3)));
					String str = ((((Arrays.deepToString(input)).replace("[","")).replace("]", "")).replace(" ", "")).replace(",", "");
					System.out.println(str.substring(0,3).replace(""," ") + "\n" + str.substring(3,6).replace(""," ") + "\n" + str.substring(6,9).replace(""," ") + "\n");
					System.out.println("Attempting to solve board: "+ (j/3));
					long startTime = System.currentTimeMillis();
					boolean solution = SolveNinePuzzle(input);
					long endTime = System.currentTimeMillis();
					double totalTimeSeconds = (endTime-startTime)/1000.0;
					System.out.println();
					if(solution)
						System.out.println("Board: "+(j/3) + " Solvable");
						averageTime += totalTimeSeconds;
					if(!solution)
						System.out.println("Board: "+ (j/3) + " is NOT solvable!");
					

					System.out.println("----------------------------------------------------------------------");
					k=0;
				}

			}
			System.out.println("Processed " + (j/3) + " boards");
			System.out.println("Average Time: " + averageTime/(j/3));
			
		}

	}

}