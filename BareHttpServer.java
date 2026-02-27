import java.net.*;
import java.io.*;

public class BareHttpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while(true){
            Socket s = server.accept();

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));

            String req = in.readLine();
            System.out.println(req);

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println("HTTP/1.1 200 OK");
            out.println();
            out.println("Hello Architect");
            out.flush();

            s.close();
        }
    }
}
