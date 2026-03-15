import java.util.*;

public class UpperRandom {

    public static void main(String[] args){

        Random r = new Random();

        StringBuilder s = new StringBuilder();

        for(int i=0;i<8;i++)
            s.append((char)(65+r.nextInt(26)));

        System.out.println(s);
    }
}
