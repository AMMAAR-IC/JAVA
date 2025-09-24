import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {
    public static void main(String[] args) {
        try {
            String apiKey = "YOUR_API_KEY"; // get free from openweathermap.org
            String city = "Mumbai";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                                + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) response.append(line);
            in.close();

            System.out.println("Weather Data: " + response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
