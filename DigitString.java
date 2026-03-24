import java.util.*;

public class DigitString {

    public static void main(String[] args){

        Random r=new Random();

        StringBuilder s=new StringBuilder();

        for(int i=0;i<10;i++)
            s.append(r.nextInt(10));

        System.out.println(s);
    }
}
