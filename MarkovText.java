import java.nio.file.*;
import java.util.*;

public class MarkovText {
    public static void main(String[] args) throws Exception {
        String text = Files.readString(Path.of("corpus.txt")).toLowerCase();
        String[] words = text.split("\\s+");

        Map<String,List<String>> chain = new HashMap<>();
        for (int i=0;i<words.length-1;i++)
            chain.computeIfAbsent(words[i],k->new ArrayList<>()).add(words[i+1]);

        String w = words[0];
        Random r = new Random();
        for (int i=0;i<50;i++){
            System.out.print(w+" ");
            List<String> next = chain.get(w);
            if (next==null) break;
            w = next.get(r.nextInt(next.size()));
        }
    }
}
