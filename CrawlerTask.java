import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

class CrawlerTask implements Runnable {
    private final String url;
    private final Set<String> visited;
    private final BlockingQueue<String> queue;

    public CrawlerTask(String url, Set<String> visited, BlockingQueue<String> queue) {
        this.url = url;
        this.visited = visited;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            if (visited.contains(url)) return;
            synchronized (visited) {
                visited.add(url);
            }

            System.out.println("Crawling: " + url);

            URL site = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(site.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                extractLinks(inputLine);
            }
            in.close();

        } catch (Exception e) {
            // ignore broken links
        }
    }

    private void extractLinks(String line) {
        String lower = line.toLowerCase();
        int index = 0;
        while ((index = lower.indexOf("href=\"", index)) != -1) {
            index += 6;
            int end = lower.indexOf("\"", index + 1);
            if (end > index) {
                String link = line.substring(index, end);
                if (link.startsWith("http")) {
                    queue.offer(link);
                }
            }
        }
    }
}

public class WebCrawler {
    private static final int MAX_THREADS = 5;
    private static final int MAX_PAGES = 30;

    public static void main(String[] args) throws Exception {
        String startURL = "https://example.com"; // change to any site
        Set<String> visited = Collections.synchronizedSet(new HashSet<>());
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer(startURL);

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        int crawled = 0;
        while (crawled < MAX_PAGES && !queue.isEmpty()) {
            String url = queue.poll();
            if (url != null && !visited.contains(url)) {
                executor.execute(new CrawlerTask(url, visited, queue));
                crawled++;
            }
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        System.out.println("\n✅ Crawling finished. Pages visited: " + visited.size());
    }
}
