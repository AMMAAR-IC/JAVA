import java.util.*;
import java.util.stream.*;

record Edge(
    int from,
    int to
){}

public class GraphBuilder {

    public static void main(String[] args){

        List<Edge> edges=
            List.of(
                new Edge(1,2),
                new Edge(1,3),
                new Edge(2,4),
                new Edge(3,4)
            );

        Map<Integer,List<Integer>> graph=

            edges.stream()
                 .collect(
                     Collectors.groupingBy(
                         Edge::from,
                         Collectors.mapping(
                             Edge::to,
                             Collectors.toList()
                         )
                     )
                 );

        System.out.println(graph);
    }
}
