import java.util.*;

public class AsciiLine {

    public static void main(String[] args){

        Random r=new Random();

        for(int i=0;i<30;i++)
            System.out.print((char)(33+r.nextInt(94)));
    }
}
