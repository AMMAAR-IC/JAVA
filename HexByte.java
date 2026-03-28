import java.util.*;

public class HexByte {

    public static void main(String[] args){

        Random r=new Random();

        System.out.printf("%02X\n", r.nextInt(256));
    }
}
