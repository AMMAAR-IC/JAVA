import java.util.*;

public class SpamFilterLite {
    static Set<String> spamWords = Set.of("win","free","money","offer");

    static boolean isSpam(String msg){
        int score = 0;
        for(String w : msg.toLowerCase().split("\\W+"))
            if(spamWords.contains(w)) score++;
        return score >= 2;
    }

    public static void main(String[] args){
        System.out.println(isSpam("Win free money offer now"));
        System.out.println(isSpam("Hello how are you"));
    }
}
