import java.util.*;
import java.util.stream.*;

record FileInfo(
    String file,
    String hash
){}

public class DuplicateFiles {

    public static void main(String[] args){

        List<FileInfo> files=
            List.of(
                new FileInfo("a.txt","abc"),
                new FileInfo("b.txt","xyz"),
                new FileInfo("c.txt","abc")
            );

        files.stream()
             .collect(
                 Collectors.groupingBy(
                     FileInfo::hash
                 )
             )
             .values()
             .stream()
             .filter(
                 list->list.size()>1
             )
             .forEach(System.out::println);
    }
}
