import java.net.*;

public class PublicIP {
    public static void main(String[] args)throws Exception{
        String ip=new String(new URL("https://api.ipify.org").openStream().readAllBytes());
        System.out.println("Public IP: "+ip);
    }
}
