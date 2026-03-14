import java.util.*;

public class HexColor {

    public static void main(String[] args){

        Random r=new Random();

        System.out.printf("#%06X\n", r.nextInt(0xFFFFFF+1));
    }
}
