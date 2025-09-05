import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class PdfLinkCrawler {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 10;

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("Searching PDFs in: " + url);
            String content = fetch(url);
            if (content == null) continue;

            for (String pdf : extractPdfs(content)) {
                System.out.println("📄 PDF found: " + pdf);
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

    private List<String> extractPdfs(String content) {
        List<String> pdfs = new ArrayList<>();
        Matcher m = Pattern.compile("href=\\\"(http[s]?://[^\\\"]+\\.pdf)\\\"").matcher(content);
        while (m.find()) pdfs.add(m.group(1));
        return pdfs;
    }

    public static void main(String[] args) {
        PdfLinkCrawler crawler = new PdfLinkCrawler();
        crawler.crawl("https://example.com");
    }
}
