import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println("Shortest path: " + ladderLength("hit", "cog", dict));
    }

    static int ladderLength(String begin, String end, Set<String> wordList) {
        if (!wordList.contains(end)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(begin);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String word = queue.poll();
                if (word.equals(end)) return level;

                for (int i = 0; i < word.length(); i++) {
                    char[] arr = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String next = new String(arr);
                        if (wordList.remove(next)) {
                            queue.add(next);
                        }
                    }
                }
            }
            level++;
        }

        return 0;
    }
}
