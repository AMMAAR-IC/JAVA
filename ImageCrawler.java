import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class ImageCrawler {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 8;

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("Crawling: " + url);
            String content = fetch(url);
            if (content == null) continue;

            for (String img : extractImages(content)) {
                System.out.println("🖼️ Image found: " + img);
            }

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

    private List<String> extractImages(String content) {
        List<String> images = new ArrayList<>();
        Matcher m = Pattern.compile("img src=\\\"(http[s]?://[^\\\"]+)\\\"").matcher(content);
        while (m.find()) images.add(m.group(1));
        return images;
    }

    public static void main(String[] args) {
        ImageCrawler crawler = new ImageCrawler();
        crawler.crawl("https://example.com");
    }
}
