import java.util.*;

public class RandomMultiply {

    public static void main(String[] args){

        Random r=new Random();

        int a=r.nextInt(10);
        int b=r.nextInt(10);

        System.out.println(a+" x "+b+" = "+(a*b));
    }
}
