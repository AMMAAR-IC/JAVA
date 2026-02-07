import java.net.*;

public class EndpointScanner {
    public static void main(String[] args) throws Exception {
        String base = "https://example.com";
        String[] paths = {"/", "/admin", "/login", "/robots.txt", "/sitemap.xml"};

        for(String p : paths){
            try{
                HttpURLConnection c = (HttpURLConnection)new URL(base+p).openConnection();
                c.setConnectTimeout(2000);
                System.out.println(p + " -> " + c.getResponseCode());
            }catch(Exception e){
                System.out.println(p + " -> FAILED");
            }
        }
    }
}
