import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class KeywordWebScraper {
    private Map<String, Set<String>> index = new HashMap<>();
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 5; // crawl depth limit

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("Fetching: " + url);
            String content = fetch(url);
            if (content == null) continue;

            // Index words
            for (String word : content.toLowerCase().split("\\W+")) {
                if (word.length() > 3) {
                    index.computeIfAbsent(word, k -> new HashSet<>()).add(url);
                }
            }

            // Extract new links
            for (String link : extractLinks(content)) {
                if (!visited.contains(link)) queue.add(link);
            }
        }
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
        Pattern p = Pattern.compile("href=\\\"(http[s]?://[^\\\"]+)\\\"");
        Matcher m = p.matcher(content);
        while (m.find()) links.add(m.group(1));
        return links;
    }

    public void search(String keyword) {
        keyword = keyword.toLowerCase();
        if (index.containsKey(keyword)) {
            System.out.println("Found keyword in:");
            for (String url : index.get(keyword)) {
                System.out.println(" - " + url);
            }
        } else {
            System.out.println("No results for: " + keyword);
        }
    }

    public static void main(String[] args) {
        KeywordWebScraper scraper = new KeywordWebScraper();
        scraper.crawl("https://example.com"); // start point

        System.out.println("\nSearch results:");
        scraper.search("example"); // search keyword
    }
}
