import java.util.*;

public class PasswordList {

    public static void main(String[] args){

        String chars="abcdefghijklmnopqrstuvwxyz0123456789";

        Random r = new Random();

        for(int i=0;i<5;i++){

            StringBuilder sb = new StringBuilder();

            for(int j=0;j<8;j++)
                sb.append(chars.charAt(r.nextInt(chars.length())));

            System.out.println(sb);
        }
    }
}
