import java.util.*;
import java.util.stream.*;

record Node(
    int parent,
    int child
){}

public class ParentChildren {

    public static void main(String[] args){

        List<Node> nodes=
            List.of(
                new Node(1,2),
                new Node(1,3),
                new Node(2,4)
            );

        Map<Integer,List<Integer>> tree=

            nodes.stream()
                 .collect(
                     Collectors.groupingBy(
                         Node::parent,
                         Collectors.mapping(
                             Node::child,
                             Collectors.toList()
                         )
                     )
                 );

        System.out.println(tree);
    }
}
