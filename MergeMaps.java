import java.util.*;
import java.util.stream.*;

public class MergeMaps {

    public static void main(String[] args){

        Map<String,Integer> m1=
            Map.of(
                "A",10,
                "B",20
            );

        Map<String,Integer> m2=
            Map.of(
                "A",30,
                "C",40
            );

        Map<String,Integer> result=

            Stream.of(m1,m2)
                  .flatMap(
                      m->m.entrySet().stream()
                  )
                  .collect(
                      Collectors.toMap(
                          Map.Entry::getKey,
                          Map.Entry::getValue,
                          Integer::sum
                      )
                  );

        System.out.println(result);
    }
}
