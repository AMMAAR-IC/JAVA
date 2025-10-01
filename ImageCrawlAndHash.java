// ImageCrawlAndHash.java
import java.net.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.regex.*;

public class ImageCrawlAndHash {
    private static final Pattern IMG = Pattern.compile("<img [^>]*src=[\"']([^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
    private static String fetch(String u) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(u).openStream()))) {
            StringBuilder sb=new StringBuilder(); String l; while ((l=br.readLine())!=null) sb.append(l);
            return sb.toString();
        } catch (Exception e) { return null; }
    }
    public static void main(String[] args) throws Exception {
        String seed = "https://example.com";
        String html = fetch(seed);
        if (html==null) return;
        Matcher m = IMG.matcher(html);
        while (m.find()) {
            String src = m.group(1);
            if (!src.startsWith("http")) src = new URL(new URL(seed), src).toString();
            System.out.println("Found image: " + src);
            try {
                BufferedImage img = ImageIO.read(new URL(src));
                if (img != null) System.out.println(" pHash: " + PerceptualHash.phash(img));
            } catch (Exception e) { System.out.println(" error reading image"); }
        }
    }
}
