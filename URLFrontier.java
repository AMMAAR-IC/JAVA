import java.util.*;

public class URLFrontier {
    static Queue<String> q = new LinkedList<>();
    static Set<String> seen = new HashSet<>();

    static void add(String url){
        if(seen.add(url)) q.add(url);
    }

    static String next(){
        return q.poll();
    }

    public static void main(String[] args){
        add("https://a.com");
        add("https://a.com");
        add("https://b.com");
        System.out.println(next());
        System.out.println(next());
    }
}
