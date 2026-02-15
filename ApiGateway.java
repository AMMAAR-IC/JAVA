import java.net.*;
import java.io.*;
import java.util.*;

public class ApiGateway {
    static Map<String,String> routes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        routes.put("/google", "https://google.com");
        routes.put("/example", "https://example.com");

        ServerSocket ss = new ServerSocket(8085);
        System.out.println("Gateway on 8085");

        while(true){
            Socket s = ss.accept();
            new Thread(() -> handle(s)).start();
        }
    }

    static void handle(Socket s){
        try(s){
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String req = in.readLine();
            if(req == null) return;

            String path = req.split(" ")[1];
            String target = routes.getOrDefault(path, null);

            PrintWriter out = new PrintWriter(s.getOutputStream());
            if(target == null){
                out.println("HTTP/1.1 404 Not Found");
                out.println();
                out.println("No route");
            } else {
                String body = new String(new URL(target).openStream().readAllBytes());
                out.println("HTTP/1.1 200 OK");
                out.println();
                out.println(body.substring(0, Math.min(body.length(), 500)));
            }
            out.flush();
        }catch(Exception ignored){}
    }
}
