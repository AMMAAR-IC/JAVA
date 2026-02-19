import java.util.*;

public class CustomLRU {
    class Node {
        int k,v;
        Node prev,next;
        Node(int k,int v){this.k=k;this.v=v;}
    }

    int cap;
    Map<Integer,Node> map=new HashMap<>();
    Node head=new Node(0,0), tail=new Node(0,0);

    public CustomLRU(int c){
        cap=c;
        head.next=tail;
        tail.prev=head;
    }

    void remove(Node n){
        n.prev.next=n.next;
        n.next.prev=n.prev;
    }

    void insert(Node n){
        n.next=head.next;
        n.prev=head;
        head.next.prev=n;
        head.next=n;
    }

    public int get(int k){
        if(!map.containsKey(k)) return -1;
        Node n=map.get(k);
        remove(n);
        insert(n);
        return n.v;
    }

    public void put(int k,int v){
        if(map.containsKey(k)) remove(map.get(k));
        Node n=new Node(k,v);
        map.put(k,n);
        insert(n);
        if(map.size()>cap){
            Node l=tail.prev;
            remove(l);
            map.remove(l.k);
        }
    }
}
