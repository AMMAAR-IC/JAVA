import java.util.*;

public class RandomTable {

    public static void main(String[] args){

        Random r=new Random();

        int n=1+r.nextInt(10);

        for(int i=1;i<=10;i++)
            System.out.println(n*i);
    }
}
