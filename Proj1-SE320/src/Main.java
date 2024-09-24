/*
*Created by: Jonathan Ortiz
* Used for class:SE320
*
* Purpose:Used to work Assignment 1.
* Shows concept of using a try-catch block to
* prompt user when they input something that is not a numebr.
* */
import java.util.Scanner;

public class Main {
    //funcion used to get the sum of the User input
    static void UserSum(int userNum1, int userNum2){
        int totalSum=userNum1+userNum2;
        System.out.println("The sum of"+ userNum1+ "+"+ userNum2+ "=" + totalSum);

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.print("Enter the first number:");
            int num1 = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter the second number:");
            int num2 = Integer.parseInt(scanner.nextLine());


            UserSum(num1,num2);

        } catch(NumberFormatException e){
            System.out.println("One of the inputs is not a number please try again");
        }

    }
}