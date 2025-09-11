import jpcap.*;
import jpcap.packet.*;

public class PacketSniffer {
    public static void main(String[] args) throws Exception {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        JpcapCaptor captor = JpcapCaptor.openDevice(devices[0], 65535, true, 20);

        System.out.println("📡 Listening on: " + devices[0].name);

        while (true) {
            Packet packet = captor.getPacket();
            if (packet != null) System.out.println(packet);
        }
    }
}
