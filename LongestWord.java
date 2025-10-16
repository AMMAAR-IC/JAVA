import java.io.*;
import java.util.*;

public class LongestWord {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("sample.txt"));
        String longest = "";
        while (sc.hasNext()) {
            String word = sc.next();
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        System.out.println("Longest word: " + longest);
    }
}
