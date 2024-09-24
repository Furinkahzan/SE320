/*
*Created by:Jonathan Ortiz
*Purpose: implementing things in class for SE320 HW1.2
*
 */


import java.util.Scanner;
import java.util.Random;

public class Main {
    //function for populating array
    public static int[] createRandomArray() {
        int MAX_ARRAY_SIZE=100, RANDOM_BOUND=1000;
        int[] randomArray = new int[MAX_ARRAY_SIZE];
        Random rand = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt(RANDOM_BOUND);  // Random integers between 0 and 999
        }

        return randomArray;  // Return the created array
    }




    public static void main(String[] args) {
        int[]randomArray = createRandomArray();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the index of the randomizes array (0-99):  ");
        try{
            int index = scanner.nextInt();
            System.out.println("Element at index"+index+":"+randomArray[index]);

        }catch(ArrayIndexOutOfBoundsException e){

            System.out.println("Input was out of bounds.");

        }catch(Exception e){
            System.out.println("Invalid input, please enter an integer.");
        }
        finally {
            scanner.close();
        }
    }
}