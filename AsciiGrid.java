import java.util.*;

public class AsciiGrid {

    public static void main(String[] args){

        Random r=new Random();

        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++)
                System.out.print((char)(33+r.nextInt(94)));
            System.out.println();
        }
    }
}
