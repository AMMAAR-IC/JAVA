import java.util.*;

public class TextSimilarity {
    public static void main(String[] args) {
        String s1 = "Machine learning is fascinating.";
        String s2 = "I love learning about AI and machines.";
        System.out.println("Similarity: " + cosine(s1, s2));
    }

    static double cosine(String a, String b) {
        Map<String,Integer> f1 = freq(a), f2 = freq(b);
        Set<String> all = new HashSet<>(f1.keySet()); all.addAll(f2.keySet());
        double dot=0, n1=0, n2=0;
        for(String w: all){
            int x=f1.getOrDefault(w,0), y=f2.getOrDefault(w,0);
            dot+=x*y; n1+=x*x; n2+=y*y;
        }
        return dot / (Math.sqrt(n1)*Math.sqrt(n2));
    }

    static Map<String,Integer> freq(String s){
        Map<String,Integer> m=new HashMap<>();
        for(String w:s.toLowerCase().split("\\W+"))
            m.put(w, m.getOrDefault(w,0)+1);
        return m;
    }
}
