import java.util.*;

public class BinaryString {

    public static void main(String[] args){

        Random r=new Random();

        StringBuilder s=new StringBuilder();

        for(int i=0;i<16;i++)
            s.append(r.nextInt(2));

        System.out.println(s);
    }
}
