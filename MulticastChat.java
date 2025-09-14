// MulticastChat.java
import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastChat {
    public static void main(String[] args) throws Exception {
        String groupAddr = args.length > 0 ? args[0] : "230.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 4446;
        InetAddress group = InetAddress.getByName(groupAddr);

        try (MulticastSocket socket = new MulticastSocket(port)) {
            socket.joinGroup(group);
            System.out.println("Joined multicast " + groupAddr + ":" + port);
            // Receiver thread
            Thread recv = new Thread(() -> {
                byte[] buf = new byte[8192];
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        socket.receive(p);
                        String msg = new String(p.getData(), 0, p.getLength());
                        System.out.println("\n[IN] " + msg);
                        System.out.print("> ");
                    } catch (IOException e) { break; }
                }
            });
            recv.setDaemon(true);
            recv.start();

            // Sender loop
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String name = System.getProperty("user.name");
            System.out.print("> ");
            String line;
            while ((line = br.readLine()) != null) {
                String out = name + ": " + line;
                byte[] data = out.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, group, port);
                socket.send(packet);
                System.out.print("> ");
            }

            socket.leaveGroup(group);
        }
    }
}
