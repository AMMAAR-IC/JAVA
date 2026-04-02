import java.util.*;

public class SignCheck {

    public static void main(String[] args){

        Random r=new Random();

        int n=r.nextInt(200)-100;

        System.out.println(n>0?"Positive":n<0?"Negative":"Zero");
    }
}
