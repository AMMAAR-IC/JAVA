import java.util.*;

public class RandomColor {

    public static void main(String[] args){

        Random r=new Random();

        int red=r.nextInt(256);
        int green=r.nextInt(256);
        int blue=r.nextInt(256);

        System.out.println("RGB("+red+","+green+","+blue+")");
    }
}
