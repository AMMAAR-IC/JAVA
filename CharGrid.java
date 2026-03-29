import java.util.*;

public class CharGrid {

    public static void main(String[] args){

        Random r=new Random();

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)
                System.out.print((char)(97+r.nextInt(26))+" ");
            System.out.println();
        }
    }
}
