import java.util.*;

public class StringListGen {

    public static void main(String[] args){

        Random r=new Random();

        List<String> list=new ArrayList<>();

        for(int i=0;i<5;i++)
            list.add("str"+r.nextInt(100));

        System.out.println(list);
    }
}
