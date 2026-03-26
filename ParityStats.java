import java.util.*;

public class ParityStats {

    public static void main(String[] args){

        Random r=new Random();

        int even=0, odd=0;

        for(int i=0;i<50;i++){
            int n=r.nextInt(100);
            if(n%2==0) even++;
            else odd++;
        }

        System.out.println("Even: "+even+" Odd: "+odd);
    }
}
