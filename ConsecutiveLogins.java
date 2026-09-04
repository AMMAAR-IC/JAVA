import java.time.*;
import java.util.*;
import java.util.stream.*;

record Login(
    String user,
    LocalDate date
){}

public class ConsecutiveLogin {

    public static void main(String[] args){

        List<Login> logins=
            List.of(
                new Login(
                    "Alex",
                    LocalDate.of(2026,8,20)
                ),
                new Login(
                    "Alex",
                    LocalDate.of(2026,8,21)
                ),
                new Login(
                    "Alex",
                    LocalDate.of(2026,8,25)
                ),
                new Login(
                    "Sam",
                    LocalDate.of(2026,8,20)
                ),
                new Login(
                    "Sam",
                    LocalDate.of(2026,8,22)
                )
            );

        logins.stream()
              .collect(
                  Collectors.groupingBy(
                      Login::user,
                      Collectors.mapping(
                          Login::date,
                          Collectors.toList()
                      )
                  )
              )
              .forEach(
                  (user,dates) -> {

                      dates.sort(
                          Comparator.naturalOrder()
                      );

                      boolean consecutive=

                          IntStream.range(
                              0,
                              dates.size()-1
                          )
                          .anyMatch(
                              i ->
                              dates.get(i+1)
                                   .equals(
                                       dates.get(i)
                                           .plusDays(1)
                                   )
                          );

                      if(consecutive)
                          System.out.println(user);
                  }
              );
    }
}
