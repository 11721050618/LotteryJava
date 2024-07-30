package lotteryjasonp;

import java.util.Random;
import java.util.Scanner;

public class LotteryJasonP {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            //stores user inputs and the random generated numbers
            int[] programNumbers = new int[3];
            int[] userNumbers = new int[3];
            
            //generate 3 random numbers between 0 and 9
            for (int i = 0; i < 3; i++) {
                programNumbers[i] = random.nextInt(10);
            }
            
            //input user numbers and validate
            for (int i = 0; i < 3; i++) {
                boolean validInput = false;//sets the while loop to false
                while (!validInput) {
                    System.out.print("Enter number " + (i + 1) + ": ");//prints out the number the user has to enter
                    if (scanner.hasNextInt()) {//checks if the users next input is a int or not
                        int num = scanner.nextInt();
                        if (num >= 0 && num <= 9) {//checks if the users input is between 0 and 9
                            userNumbers[i] = num;
                            validInput = true;//breaks the while loop
                        } else {
                            System.out.println("Invalid input. Please enter a valid single-digit number (0-9).");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid single-digit number (0-9).");
                        scanner.next(); //clear the invalid input from the scanner
                    }
                }
            }
            
            
            //compare user numbers with program numbers
            int matchingCount = 0;
            boolean[] matched = new boolean[3]; //to keep track of matched program numbers
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (userNumbers[i] == programNumbers[j] && !matched[j]) {
                        matchingCount++;
                        matched[j] = true; //mark program number as matched
                        break; //exit inner loop to avoid counting a repeated match
                    }
                }
            }
            
            // Determine the prize based on matching count
            int prize = 0;
            if (matchingCount >= 1) {
                prize = 10;
            }
            if (matchingCount >= 2) {
                prize = 100;
            }
            if (matchingCount == 3) {
                boolean isOrderedMatch = true;
                for (int i = 0; i < 3; i++) {
                    if (userNumbers[i] != programNumbers[i]) {
                        isOrderedMatch = false;
                        break;
                    }
                }
                prize = isOrderedMatch ? 1000000 : 1000;
            }

            // Display results
            System.out.println("Program Numbers: " + programNumbers[0] + " " + programNumbers[1] + " " + programNumbers[2]);
            System.out.println("Your Numbers: " + userNumbers[0] + " " + userNumbers[1] + " " + userNumbers[2]);
            System.out.println("Matching Count: " + matchingCount);
            System.out.println("You win $" + prize);
        }
    }
}
