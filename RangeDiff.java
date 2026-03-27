import java.util.*;

public class RangeDiff {

    public static void main(String[] args){

        Random r=new Random();

        int min=10+r.nextInt(40);
        int max=min+r.nextInt(50);

        System.out.println(max-min);
    }
}
