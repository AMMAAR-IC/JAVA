import java.net.*;
import java.io.*;

public class HttpProxyLogger {
    public static void main(String[] args) throws Exception {
        ServerSocket proxy = new ServerSocket(8888);
        System.out.println("Proxy running on port 8888");

        while(true){
            Socket client = proxy.accept();
            new Thread(() -> handle(client)).start();
        }
    }

    static void handle(Socket client){
        try(client){
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line;
            while((line=in.readLine())!=null && !line.isEmpty()){
                System.out.println(line);
            }
        }catch(Exception ignored){}
    }
}
