import java.io.*;
import java.net.*;

public class MiniWebServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Mini Web Server running at http://localhost:8080/");
        
        while (true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            OutputStream out = client.getOutputStream();
            
            String request = in.readLine();
            if (request == null) continue;
            
            String fileName = request.split(" ")[1].equals("/") ? "index.html" : request.split(" ")[1].substring(1);
            File file = new File(fileName);
            
            if (!file.exists()) {
                String errorMsg = "HTTP/1.1 404 Not Found\r\n\r\nFile not found";
                out.write(errorMsg.getBytes());
            } else {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = fis.readAllBytes();
                out.write(("HTTP/1.1 200 OK\r\nContent-Length: " + data.length + "\r\n\r\n").getBytes());
                out.write(data);
                fis.close();
            }
            out.flush();
            client.close();
        }
    }
}
