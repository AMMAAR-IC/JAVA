// BSTVisualizer.java
class Node { int v; Node l,r; Node(int v){this.v=v;} }

public class BSTVisualizer {
    static Node insert(Node n,int v){
        if(n==null) return new Node(v);
        if(v<n.v) n.l=insert(n.l,v);
        else n.r=insert(n.r,v);
        return n;
    }

    static void print(Node n,int depth){
        if(n==null)return;
        print(n.r, depth+1);
        System.out.println(" ".repeat(depth*4)+n.v);
        print(n.l, depth+1);
    }

    public static void main(String[] args){
        Node root=null;
        for(int x:new int[]{20,10,30,5,15,25,40}) root=insert(root,x);
        print(root,0);
    }
}
