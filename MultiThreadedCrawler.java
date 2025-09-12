// MultiThreadedCrawler.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MultiThreadedCrawler {
    private final Set<String> visited = ConcurrentHashMap.newKeySet();
    private final ExecutorService pool;
    private final BlockingQueue<String> frontier = new LinkedBlockingQueue<>();
    private final int maxPages;
    private final Pattern LINK = Pattern.compile("href=[\"'](http[s]?://[^\"'#>\\s]+)[\"']", Pattern.CASE_INSENSITIVE);

    public MultiThreadedCrawler(int threads, int maxPages) {
        this.pool = Executors.newFixedThreadPool(threads);
        this.maxPages = maxPages;
    }

    public void start(String seed) throws InterruptedException {
        frontier.offer(seed);

        while (!pool.isShutdown()) {
            if (visited.size() >= maxPages) break;
            final String url = frontier.poll(2, TimeUnit.SECONDS);
            if (url == null) {
                // queue empty; wait for running tasks
                if (((ThreadPoolExecutor) pool).getActiveCount() == 0) break;
                continue;
            }
            if (visited.contains(url)) continue;
            pool.execute(() -> crawl(url));
        }

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.MINUTES);
        System.out.println("\nDone. Visited: " + visited.size());
    }

    private void crawl(String url) {
        if (visited.size() >= maxPages) return;
        if (!visited.add(url)) return;
        System.out.println("Crawling: " + url);
        String html = fetch(url);
        if (html == null) return;

        Matcher m = LINK.matcher(html);
        while (m.find() && visited.size() < maxPages) {
            String link = m.group(1);
            // basic normalization
            if (link.endsWith("/")) link = link.substring(0, link.length() - 1);
            if (!visited.contains(link)) frontier.offer(link);
        }
    }

    private String fetch(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "AmmaarCrawler/1.0");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int code = con.getResponseCode();
            if (code >= 400) return null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append('\n');
                return sb.toString();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String seed = args.length > 0 ? args[0] : "https://example.com";
        MultiThreadedCrawler c = new MultiThreadedCrawler(8, 100); // 8 threads, crawl up to 100 pages
        c.start(seed);
    }
}
