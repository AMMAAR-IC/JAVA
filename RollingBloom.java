import java.util.*;

public class RollingBloom {
    static BitSet a = new BitSet(1024);
    static BitSet b = new BitSet(1024);
    static boolean flip = false;

    static void add(String s){
        BitSet x = flip ? b : a;
        x.set(Math.abs(s.hashCode()) % 1024);
    }

    static boolean has(String s){
        int i = Math.abs(s.hashCode()) % 1024;
        return a.get(i) || b.get(i);
    }

    static void rotate(){
        if(flip) b.clear(); else a.clear();
        flip = !flip;
    }

    public static void main(String[] args){
        add("java");
        System.out.println(has("java"));
        rotate();
        System.out.println(has("java"));
    }
}
