import java.util.*;

public class RandomMax {

    public static void main(String[] args){

        Random r=new Random();

        int max=0;

        for(int i=0;i<10;i++){
            int n=r.nextInt(100);
            if(n>max) max=n;
        }

        System.out.println(max);
    }
}
