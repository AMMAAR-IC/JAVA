import java.net.*;
import java.io.*;

public class EchoServer {

    public static void main(String[] args) throws Exception {

        ServerSocket ss=new ServerSocket(5000);

        while(true){

            Socket s=ss.accept();

            BufferedReader in=new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            PrintWriter out=new PrintWriter(
                    s.getOutputStream(),true);

            String msg=in.readLine();

            out.println("Echo: "+msg);
        }
    }
}
