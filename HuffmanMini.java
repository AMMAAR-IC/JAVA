
import java.util.*;

public class HuffmanMini {
    static class Node implements Comparable<Node>{
        char c; int f; Node l,r;
        Node(char c,int f){this.c=c;this.f=f;}
        Node(Node l,Node r){this.l=l;this.r=r;this.f=l.f+r.f;}
        public int compareTo(Node o){ return this.f-o.f; }
    }

    static void build(Node n,String s,Map<Character,String> map){
        if(n.l==null && n.r==null){ map.put(n.c,s); return; }
        build(n.l,s+"0",map);
        build(n.r,s+"1",map);
    }

    public static void main(String[] args){
        String text="aaabbcdddd";
        Map<Character,Integer> freq=new HashMap<>();
        for(char c:text.toCharArray()) freq.put(c,freq.getOrDefault(c,0)+1);

        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(var e:freq.entrySet()) pq.add(new Node(e.getKey(),e.getValue()));

        while(pq.size()>1){
            pq.add(new Node(pq.poll(),pq.poll()));
        }

        Map<Character,String> codes=new HashMap<>();
        build(pq.poll(),"",codes);
        System.out.println(codes);
    }
}
