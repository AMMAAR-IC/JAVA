import java.nio.file.*;
import java.util.*;

public class WordFreq {

    public static void main(String[] args) throws Exception{

        String text = Files.readString(Path.of("file.txt"));

        Map<String,Integer> map = new HashMap<>();

        for(String w : text.split("\\W+"))
            map.merge(w.toLowerCase(),1,Integer::sum);

        System.out.println(map);
    }
}
