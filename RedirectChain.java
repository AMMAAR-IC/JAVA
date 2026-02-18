import java.net.*;

public class RedirectChain {
    public static void main(String[] args) throws Exception {
        String url = "http://google.com";

        for(int i=0;i<5;i++){
            HttpURLConnection c = (HttpURLConnection)new URL(url).openConnection();
            c.setInstanceFollowRedirects(false);

            int code = c.getResponseCode();
            System.out.println(url + " -> " + code);

            if(code==301 || code==302 || code==307 || code==308){
                url = c.getHeaderField("Location");
                if(!url.startsWith("http")) break;
            } else break;
        }
    }
}
