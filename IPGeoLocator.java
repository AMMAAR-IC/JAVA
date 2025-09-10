import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class IPGeoLocator {
    public static void main(String[] args) throws Exception {
        String ip = "8.8.8.8";
        String url = "https://ipinfo.io/" + ip + "/json";

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);

        JSONObject obj = new JSONObject(sb.toString());
        System.out.println("🌍 IP Info: " + obj.toString(2));
    }
}
