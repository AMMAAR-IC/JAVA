import java.util.*;

public class RandomMAC {

    public static void main(String[] args){

        Random r = new Random();

        for(int i=0;i<6;i++){
            System.out.printf("%02X", r.nextInt(256));
            if(i<5) System.out.print(":");
        }
    }
}
