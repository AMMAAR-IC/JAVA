import java.util.*;

public class RandomPrime {

    static boolean isPrime(int n){
        if(n<2) return false;
        for(int i=2;i<=Math.sqrt(n);i++)
            if(n%i==0) return false;
        return true;
    }

    public static void main(String[] args){

        Random r=new Random();

        int n;
        do{
            n=r.nextInt(100);
        }while(!isPrime(n));

        System.out.println(n);
    }
}
