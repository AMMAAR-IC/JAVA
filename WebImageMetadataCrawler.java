import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class WebImageMetadataCrawler {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 5; // number of pages to crawl
    private static final int IMG_LIMIT = 5; // images per page

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("🌐 Scanning page: " + url);
            String content = fetch(url);
            if (content == null) continue;

            List<String> images = extractImages(content);
            int count = 0;

            for (String imgUrl : images) {
                if (count++ >= IMG_LIMIT) break;
                extractImageMetadata(imgUrl);
            }

            for (String link : extractLinks(content)) {
                if (!visited.contains(link)) queue.add(link);
            }
        }
    }

    private String fetch(String url) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new URL(url).openStream()));
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

    private void extractImageMetadata(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            BufferedImage img = ImageIO.read(url);

            if (img != null) {
                System.out.println("   🖼️ Image: " + imgUrl);
                System.out.println("      Width: " + img.getWidth() + " px");
                System.out.println("      Height: " + img.getHeight() + " px");
                System.out.println("      Format: " + getFormat(imgUrl));
            }
        } catch (Exception e) {
            System.out.println("   ❌ Could not read image: " + imgUrl);
        }
    }

    private String getFormat(String url) {
        if (url.toLowerCase().endsWith(".jpg") || url.toLowerCase().endsWith(".jpeg")) return "JPEG";
        if (url.toLowerCase().endsWith(".png")) return "PNG";
        if (url.toLowerCase().endsWith(".gif")) return "GIF";
        return "Unknown";
    }

    public static void main(String[] args) {
        WebImageMetadataCrawler crawler = new WebImageMetadataCrawler();
        crawler.crawl("https://example.com");
    }
}
