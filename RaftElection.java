import java.util.*;

public class RaftElection {
    static class Node{
        int id;
        boolean leader=false;

        Node(int id){this.id=id;}
    }

    public static void main(String[] args){
        List<Node> nodes=new ArrayList<>();
        for(int i=0;i<5;i++) nodes.add(new Node(i));

        Node leader = nodes.get(new Random().nextInt(nodes.size()));
        leader.leader=true;

        System.out.println("Leader elected: "+leader.id);
    }
}
