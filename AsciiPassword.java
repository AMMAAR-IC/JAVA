import java.util.*;

public class AsciiPassword {

    public static void main(String[] args){

        Random r=new Random();

        for(int i=0;i<12;i++)
            System.out.print((char)(33+r.nextInt(94)));
    }
}
