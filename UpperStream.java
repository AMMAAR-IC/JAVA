import java.util.*;

public class UpperStream {

    public static void main(String[] args){

        List<String> list=List.of("java","code","ai");

        list.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }
}
