import java.net.*;
import java.io.*;

public class TcpSniffer {
    public static void main(String[] args)throws Exception{
        ServerSocket ss=new ServerSocket(5555);
        System.out.println("Listening on 5555...");

        while(true){
            Socket s=ss.accept();
            new Thread(() -> handle(s)).start();
        }
    }

    static void handle(Socket s){
        try(s){
            s.getInputStream().transferTo(System.out);
        }catch(Exception ignored){}
    }
}
