import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class MetadataCrawler {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 6;

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("📄 Page: " + url);
            String content = fetch(url);
            if (content == null) continue;

            String title = extract(content, "<title>(.*?)</title>");
            String desc = extract(content, "<meta name=\\\"description\\\" content=\\\"(.*?)\\\"");

            System.out.println("   Title: " + (title.isEmpty() ? "N/A" : title));
            System.out.println("   Description: " + (desc.isEmpty() ? "N/A" : desc));

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
        Matcher m = Pattern.compile("href=\\\"(http[s]?://[^\\\"]+)\\\"").matcher(content);
        while (m.find()) links.add(m.group(1));
        return links;
    }

    private String extract(String content, String regex) {
        Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(content);
        return m.find() ? m.group(1) : "";
    }

    public static void main(String[] args) {
        MetadataCrawler crawler = new MetadataCrawler();
        crawler.crawl("https://example.com");
    }
}
