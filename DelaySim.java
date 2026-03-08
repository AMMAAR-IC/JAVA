import java.util.*;

public class DelaySim {

    public static void main(String[] args) throws Exception{

        Random r=new Random();

        int delay=r.nextInt(2000);

        Thread.sleep(delay);

        System.out.println("Delay "+delay+" ms");
    }
}
