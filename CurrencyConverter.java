// CurrencyConverter.java
import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.exchangerate-api.com/v4/latest/USD");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            JSONObject json = new JSONObject(sb.toString());

            double rate = json.getJSONObject("rates").getDouble("INR");
            double usd = 10;
            System.out.println(usd + " USD = " + (usd * rate) + " INR");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
