// RobotsCrawler.java
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class RobotsCrawler {
    private static boolean allowed(String base, String path) {
        try {
            URL u = new URL(base + "/robots.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
            String line; boolean userAgentStar = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.toLowerCase().startsWith("user-agent:")) {
                    userAgentStar = line.substring(11).trim().equals("*");
                } else if (userAgentStar && line.toLowerCase().startsWith("disallow:")) {
                    String dis = line.substring(9).trim();
                    if (!dis.isEmpty() && path.startsWith(dis)) return false;
                } else if (line.isEmpty()) userAgentStar = false;
            }
        } catch (Exception ignored) {}
        return true;
    }

    public static void crawl(String seed, int max) throws Exception {
        Queue<String> q = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        URL s = new URL(seed);
        String base = s.getProtocol() + "://" + s.getHost();
        q.add(seed); seen.add(seed);
        while (!q.isEmpty() && seen.size() < max) {
            String cur = q.remove();
            System.out.println("Visiting: " + cur);
            URL u = new URL(cur);
            String path = u.getPath();
            if (!allowed(base, path)) { System.out.println("Skipped by robots: " + cur); continue; }
            String html = new String(u.openStream().readAllBytes());
            Matcher m = Pattern.compile("href=[\"']([^\"'#]+)[\"']", Pattern.CASE_INSENSITIVE).matcher(html);
            while (m.find() && seen.size() < max) {
                String link = m.group(1);
                if (!link.startsWith("http")) link = new URL(u, link).toString();
                if (link.startsWith(base) && seen.add(link)) q.add(link);
            }
            Thread.sleep(300); // polite
        }
    }

    public static void main(String[] args) throws Exception {
        crawl("https://example.com", 30);
    }
}
