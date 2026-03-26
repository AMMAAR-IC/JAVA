import java.util.*;

public class AvgRandom {

    public static void main(String[] args){

        Random r=new Random();

        int sum=0;

        for(int i=0;i<20;i++)
            sum+=r.nextInt(100);

        System.out.println(sum/20.0);
    }
}
