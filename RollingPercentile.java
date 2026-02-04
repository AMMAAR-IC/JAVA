import java.util.*;

public class RollingPercentile {
    static List<Integer> data=new ArrayList<>();

    static int p(int k){
        Collections.sort(data);
        return data.get((int)Math.ceil(k/100.0*data.size())-1);
    }

    public static void main(String[] args){
        data.addAll(List.of(10,20,30,40,50));
        System.out.println(p(90));
    }
}
