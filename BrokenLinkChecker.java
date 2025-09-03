import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class BrokenLinkChecker {
    private Set<String> visited = new HashSet<>();
    private Queue<String> queue = new LinkedList<>();
    private static final int LIMIT = 15; // max pages

    public void crawl(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty() && visited.size() < LIMIT) {
            String url = queue.poll();
            if (visited.contains(url)) continue;
            visited.add(url);

            System.out.println("\nScanning page: " + url);
            String content = fetch(url);
            if (content == null) continue;

            for (String link : extractLinks(content)) {
                checkLink(link);
                if (!visited.contains(link)) queue.add(link);
            }
        }
    }

    private String fetch(String url) {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(new URL(url).openStream());
            while (sc.hasNextLine()) sb.append(sc.nextLine());
            sc.close();
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

    private void checkLink(String link) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
            conn.setRequestMethod("HEAD");
            int code = conn.getResponseCode();
            if (code >= 200 && code < 400)
                System.out.println("[✅ WORKING] " + link);
            else
                System.out.println("[❌ BROKEN] " + link + " (Code: " + code + ")");
        } catch (Exception e) {
            System.out.println("[⚠️ ERROR] " + link);
        }
    }

    public static void main(String[] args) {
        BrokenLinkChecker checker = new BrokenLinkChecker();
        checker.crawl("https://example.com"); // change this to any site
    }
}
