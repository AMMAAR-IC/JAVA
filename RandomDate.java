import java.util.*;

public class RandomDate {

    public static void main(String[] args){

        Random r = new Random();

        int day = 1+r.nextInt(28);
        int month = 1+r.nextInt(12);
        int year = 2000+r.nextInt(25);

        System.out.println(day+"/"+month+"/"+year);
    }
}
