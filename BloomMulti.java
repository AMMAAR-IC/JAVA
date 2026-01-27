import java.util.*;

public class BloomMulti {
    static BitSet b = new BitSet(1024);

    static void add(String s){
        for(int i=0;i<3;i++)
            b.set(Math.abs((s+i).hashCode()) % 1024);
    }

    static boolean has(String s){
        for(int i=0;i<3;i++)
            if(!b.get(Math.abs((s+i).hashCode()) % 1024)) return false;
        return true;
    }

    public static void main(String[] args){
        add("java");
        System.out.println(has("java"));
        System.out.println(has("python"));
    }
}
