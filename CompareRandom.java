import java.util.*;

public class CompareRandom {

    public static void main(String[] args){

        Random r=new Random();

        int a=r.nextInt(100);
        int b=r.nextInt(100);

        System.out.println(a+" vs "+b);

        System.out.println(a>b?"A bigger":"B bigger");
    }
}
