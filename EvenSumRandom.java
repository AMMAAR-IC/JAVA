import java.util.*;

public class EvenSumRandom {

    public static void main(String[] args){

        Random r=new Random();

        int sum=0;

        for(int i=0;i<10;i++){
            int n=r.nextInt(50);
            if(n%2==0) sum+=n;
        }

        System.out.println(sum);
    }
}
