import java.util.*;
import java.util.stream.*;

record Edge(
    int a,
    int b
){}

public class ConnectedComponents {

    public static void main(String[] args){

        List<Edge> edges=
            List.of(
                new Edge(1,2),
                new Edge(2,3),
                new Edge(4,5)
            );

        Map<Integer,Set<Integer>> graph=

            edges.stream()
                 .flatMap(
                     e->Stream.of(
                         Map.entry(e.a(),e.b()),
                         Map.entry(e.b(),e.a())
                     )
                 )
                 .collect(
                     Collectors.groupingBy(
                         Map.Entry::getKey,
                         Collectors.mapping(
                             Map.Entry::getValue,
                             Collectors.toSet()
                         )
                     )
                 );

        System.out.println(graph);
    }
}
