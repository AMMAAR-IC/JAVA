import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DuplicateDomains {

    public static void main(String[] args){

        List<String> emails=
            List.of(
                "a@gmail.com",
                "b@yahoo.com",
                "c@gmail.com",
                "d@yahoo.com",
                "e@test.com"
            );

        emails.stream()
              .map(
                  s->s.substring(
                      s.indexOf("@")+1
                  )
              )
              .collect(
                  Collectors.groupingBy(
                      Function.identity(),
                      Collectors.counting()
                  )
              )
              .entrySet()
              .stream()
              .filter(
                  e->e.getValue()>1
              )
              .forEach(System.out::println);
    }
}
