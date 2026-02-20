import java.util.*;

public class TokenRingSim {
    static class Node {
        int id;
        Node(int id){ this.id=id; }
        void receiveToken(){
            System.out.println("Node "+id+" has token");
        }
    }

    public static void main(String[] args){
        List<Node> ring = new ArrayList<>();
        for(int i=0;i<5;i++) ring.add(new Node(i));

        for(int round=0;round<3;round++){
            for(Node n:ring){
                n.receiveToken();
            }
        }
    }
}
