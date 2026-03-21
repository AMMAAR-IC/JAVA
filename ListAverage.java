import java.util.*;

public class ListAverage {

    public static void main(String[] args){

        Random r=new Random();

        int sum=0;

        for(int i=0;i<10;i++){
            int n=r.nextInt(100);
            sum+=n;
        }

        System.out.println(sum/10.0);
    }
}
