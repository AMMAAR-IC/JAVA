import java.util.*;

public class DelayPrint {

    public static void main(String[] args) throws Exception {

        Random r=new Random();

        for(int i=0;i<5;i++){
            Thread.sleep(r.nextInt(1000));
            System.out.println("Step "+i);
        }
    }
}
