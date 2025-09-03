import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class FocusedWebCrawler {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private List<String> relevantPages = new ArrayList<>();
    private static final int LIMIT = 10;

    private String keyword;

    public FocusedWebCrawler(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("Scanning: " + url);
            String content = fetch(url);
            if (content == null) continue;

            if (content.toLowerCase().contains(keyword)) {
                System.out.println("✅ Relevant page found: " + url);
                relevantPages.add(url);
            }

            for (String link : extractLinks(content)) {
                if (!visited.contains(link)) queue.add(link);
            }
        }

        System.out.println("\n=== Relevant Pages for \"" + keyword + "\" ===");
        for (String page : relevantPages) {
            System.out.println(page);
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

    public static void main(String[] args) {
        FocusedWebCrawler crawler = new FocusedWebCrawler("java"); // keyword = "java"
        crawler.crawl("https://example.com"); // start URL
    }
}
