import java.nio.ByteBuffer;

public class PacketSerializer {
    public static void main(String[] args){
        ByteBuffer b = ByteBuffer.allocate(128);

        b.putInt(2001);
        b.putLong(System.currentTimeMillis());
        b.put("HELLO".getBytes());

        byte[] packet = b.array();
        System.out.println("Packet size: " + packet.length);
    }
}
