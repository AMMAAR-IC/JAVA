// IPGeolocation.java
import java.io.*;
import java.net.*;
import java.util.*;

public class IPGeolocation {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP address: ");
        String ip = sc.nextLine();
        URL url = new URL("http://ip-api.com/json/" + ip);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line, json = "";
        while ((line = br.readLine()) != null) json += line;
        System.out.println("Geo Info: " + json);
    }
}
