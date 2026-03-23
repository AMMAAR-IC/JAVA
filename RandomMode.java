import java.util.*;

public class RandomMode {

    public static void main(String[] args){

        Random r=new Random();
        Map<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<20;i++){
            int n=r.nextInt(5);
            map.merge(n,1,Integer::sum);
        }

        int max=0,mode=-1;

        for(var e:map.entrySet()){
            if(e.getValue()>max){
                max=e.getValue();
                mode=e.getKey();
            }
        }

        System.out.println("Mode: "+mode);
    }
}
