import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class MiniSearchEngine {
    private Map<String, Integer> ranking = new HashMap<>();
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 10;

    public void crawl(String startUrl, String keyword) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            String content = fetch(url);
            if (content == null) continue;

            int count = countOccurrences(content.toLowerCase(), keyword.toLowerCase());
            ranking.put(url, count);

            for (String link : extractLinks(content)) {
                if (!visited.contains(link)) queue.add(link);
            }
        }

        ranking.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue() + " hits"));
    }

    private String fetch(String url) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            br.close();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private List<String> extractLinks(String content) {
        List<String> links = new ArrayList<>();
        Matcher m = Pattern.compile("href=\\\"(http[s]?://[^\\\"]+)\\\"").matcher(content);
        while (m.find()) links.add(m.group(1));
        return links;
    }

    private int countOccurrences(String text, String word) {
        return text.split(word, -1).length - 1;
    }

    public static void main(String[] args) {
        MiniSearchEngine engine = new MiniSearchEngine();
        engine.crawl("https://example.com", "example"); // rank by keyword "example"
    }
}
