import java.util.Random;
import java.util.Scanner;

public class Rock_paper_scissor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int i = 0;  
        int j = 0; 

        System.out.print("Enter how many rounds you want to play: ");
        int l = scanner.nextInt();

        System.out.println("Ok, we are playing " + l + " rounds");

        for (int k = 0; k < l; k++) {

            int x = random.nextInt(3) + 1; 

            System.out.print("Enter your choice \n1.Rock \n2.Paper \n3.Scissors\n(Choose from 1, 2, 3): ");
            int choice = scanner.nextInt();

            if (x == 1 && choice == 1) {
                System.out.println("I choose Rock");
                System.out.println("Ahh shit... it's a tie\n\n");

            } else if (x == 2 && choice == 1) {
                System.out.println("I choose Paper");
                System.out.println("Loser... I get a point");
                i++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " +j+"\n\n");

            } else if (x == 3 && choice == 1) {
                System.out.println("I choose Scissors");
                System.out.println("You win... you get a point");
                j++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " +j+"\n\n");

            } else if (x == 1 && choice == 2) {
                System.out.println("I choose Rock");
                System.out.println("You win... you get a point");
                j++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " +j+"\n\n");

            } else if (x == 2 && choice == 2) {
                System.out.println("I choose Paper");
                System.out.println("Ahh... it's a tie\n\n");

            } else if (x == 3 && choice == 2) {
                System.out.println("I choose Scissors");
                System.out.println("You lose... I get a point");
                i++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " + j+"\n\n");

            } else if (x == 1 && choice == 3) {
                System.out.println("I choose Rock");
                System.out.println("You lose... I get a point");
                i++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " + j+"\n\n");

            } else if (x == 2 && choice == 3) {
                System.out.println("I choose Paper");
                System.out.println("You win... you get a point");
                j++;
                System.out.println("My points: " + i);
                System.out.println("Your points: " + j+"\n\n");

            } else if (x == 3 && choice == 3) {
                System.out.println("I choose Scissors");
                System.out.println("Ahh... it's a tie"+"\n\n");

            } else {
                System.out.println("Please choose a number from 1 to 3");
            }
        }

        System.out.println("Final score: ");
        System.out.println("My points: " + i);
        System.out.println("Your points: " + j);
        if (i > j) {

            System.out.print("u are a loser..... sun of a bitch");

        }
        else if(j>i){

            System.out.print("fuck man.... i lose");

        }
        else{

            System.out.print("i wanna play again i'll win");
        }
    }
}