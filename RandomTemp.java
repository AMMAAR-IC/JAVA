import java.util.*;

public class RandomTemp {

    public static void main(String[] args){

        Random r=new Random();

        int temp = -10 + r.nextInt(51);

        System.out.println(temp+"°C");
    }
}
