import java.util.Scanner;

public class PasswordCracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        String chars = "abcdefghijklmnopqrstuvwxyz";

        for(char a: chars.toCharArray())
        for(char b: chars.toCharArray())
        for(char c: chars.toCharArray()){
            String guess = ""+a+b+c;

            System.out.println("Trying: "+guess);

            if(guess.equals(pass)){
                System.out.println("Password cracked: "+guess);
                return;
            }
        }

        System.out.println("Password not cracked.");
    }
}
