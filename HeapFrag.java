import java.util.*;

public class HeapFrag {
    public static void main(String[] args){
        List<byte[]> l = new ArrayList<>();
        for(int i=0;i<100;i++){
            l.add(new byte[1024 * (i%5+1)]);
            if(i%3==0) l.remove(0);
        }
        System.out.println("Done");
    }
}
