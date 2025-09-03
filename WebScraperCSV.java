import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class WebScraperCSV {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 8;

    public void crawl(String startUrl, String outputFile) {
        queue.add(startUrl);

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("URL,Title");

            while (!queue.isEmpty() && visited.size() < LIMIT) {
                String url = queue.poll();
                if (visited.contains(url)) continue;
                visited.add(url);

                System.out.println("Scraping: " + url);
                String content = fetch(url);
                if (content == null) continue;

                String title = extractTitle(content).replace(",", " "); // clean commas
                writer.println(url + "," + title);

                for (String link : extractLinks(content)) {
                    if (!visited.contains(link)) queue.add(link);
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
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

    private String extractTitle(String content) {
        Pattern p = Pattern.compile("<title>(.*?)</title>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        return m.find() ? m.group(1) : "No Title";
    }

    public static void main(String[] args) {
        WebScraperCSV scraper = new WebScraperCSV();
        scraper.crawl("https://example.com", "output.csv");
        System.out.println("\n✅ Data saved into output.csv");
    }
}
