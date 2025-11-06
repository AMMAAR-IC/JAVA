import java.io.*;
import java.net.*;
public class WhoisLookup {
    public static void main(String[] args) throws Exception {
        String domain = "example.com";
        Socket s = new Socket("whois.verisign-grs.com", 43);
        OutputStream out = s.getOutputStream();
        out.write((domain + "\r\n").getBytes());
        out.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line; while ((line = br.readLine()) != null) System.out.println(line);
        s.close();
    }
}
