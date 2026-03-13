import java.util.*;

public class HexGen {

    public static void main(String[] args){

        Random r = new Random();

        for(int i=0;i<16;i++)
            System.out.print(Integer.toHexString(r.nextInt(16)));
    }
}
