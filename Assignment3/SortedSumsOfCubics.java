/**
* This program has a nested class which is an ArrayList implementation of a min Heap. 
* @author Gurjyot Grewal
* ID: V00820022
* July 8, 2016 
*
*/  
import java.util.ArrayList;
import java.util.InputMismatchException;

public class SortedSumsOfCubics{

/*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
*    BEGINNING OF NESTED CLASS - - - HEAP IMPLEMENTATION WITH ARRAY LIST - - - Insert - O(n) - - - Delete - O(n) - TopKey,I,J - O(1)
*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    
    static class Heap {    
        

        private ArrayList<Element> heap = new ArrayList<Element>();

        private class Element{
            int key;
            int i;
            int j;

            Element(int key, int i, int j){
                this.key = key;
                this.i = i;
                this.j = j;
            }

            Element(){}
        }
        /* Return true if heap is empty */
        public boolean isEmpty(){
            return heap.isEmpty();
        }
        /* Returns n^3 */
        public int Cubed(int n){
            return (n*n*n);
        }

        public void insert(int i, int j){
            Element e = new Element(Cubed(i)+Cubed(j), i, j);
            heap.add(e);
            //send the last element up as much as you can
            
            int curr = heap.size() - 1;

            while((curr - 1)/2 >= 0){

                int min = (curr-1)/2;

                if(heap.get(curr).key < heap.get(min).key){
                    int tempKey = heap.get(curr).key;
                    int tempI = heap.get(curr).i;
                    int tempJ = heap.get(curr).j;
                    
                    heap.get(curr).key = heap.get(min).key;
                    heap.get(curr).i = heap.get(min).i;
                    heap.get(curr).j = heap.get(min).j;

                    heap.get(min).key = tempKey;
                    heap.get(min).i = tempI;
                    heap.get(min).j = tempJ;

                    curr = min;

                }else{
                    break;
                }
            }
        } 

        public void delete(){
            heap.set(0, heap.get(heap.size() -1));
            heap.remove(heap.size()-1);
            
            int cur = 0;

            //send the element at root down as much as you can
            while(2*cur + 1 < heap.size()){

                int min = 2*cur + 1;

                if(2*cur + 2 < heap.size() && heap.get(2 * cur + 2).key < heap.get(min).key ){
                    min = 2*cur + 2;
                }

                

                if(heap.get(min).key < heap.get(cur).key){
                    int tempKey = heap.get(cur).key;
                    int tempI = heap.get(cur).i;
                    int tempJ = heap.get(cur).j;

                    heap.get(cur).key = heap.get(min).key;
                    heap.get(cur).i = heap.get(min).i;
                    heap.get(cur).j = heap.get(min).j;

                    heap.get(min).key = tempKey;
                    heap.get(min).i = tempI;
                    heap.get(min).j = tempJ;

                    cur = min;
                }
                else{
                    break;
                }

            }
        }

        public int topKey(){
            heap.get(0); 

            if (isEmpty()) return Integer.MAX_VALUE;
            return heap.get(0).key;
        }

        public int topI(){
            heap.get(0);

            if (isEmpty()) return Integer.MAX_VALUE;

            return heap.get(0).i;
            
        }

        public int topJ(){
            heap.get(0);

            if (isEmpty()) return Integer.MAX_VALUE;

            return heap.get(0).j;
            
        }
    }

/*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 *   END OF NESTED CLASS - - - HEAP IMPLEMENTATION WITH ARRAY LIST
 *- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    /* This program takes an integer input n, and prints a sorted sequence of elements in the form of (i^3 + j^3),
    *  where i and j are integers in the range [0,n]
    *  The program uses O(n) space as it stores all elements in a heap and at no time is the heap larger than n+1 elements
    *  The program has O(n^2 log n) time efficiency
    */

    public static void SortedSumsOfCubics(int n){
        Heap heap = new Heap();
        
        for (int i = 0;i<=n ; i++ ) {
            heap.insert(i, 0);
            
        } 
       
        System.out.println();
        while((heap.topI()+heap.topJ())!=2*n && heap.topJ() <=n){
            

                if(heap.topJ()<heap.topI()){
                    System.out.print(heap.topKey() + ",");

                    int tempJ = heap.topJ();
                    int tempI = heap.topI();

                    heap.delete();

                    heap.insert(tempI, tempJ+1);
                }

                else{
                    System.out.print(heap.topKey() + ",");
                    heap.delete();
                }
            }

            System.out.println(heap.topKey());
            System.out.println();
            heap.delete();
        

    }



    public static void main(String[] args) {
        
        int i = Integer.parseInt(args[0]);
        SortedSumsOfCubics(i);

    }
}
