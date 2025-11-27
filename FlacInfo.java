import java.io.*;

public class FlacInfo {
    public static void main(String[] args) throws Exception {
        try (var in = new DataInputStream(new FileInputStream("audio.flac"))) {

            byte[] magic = in.readNBytes(4);
            if (!new String(magic).equals("fLaC")) {
                System.out.println("Not FLAC");
                return;
            }

            int header = in.readUnsignedByte();
            boolean last = (header & 0x80) != 0;
            int type = header & 0x7F;
            int length = (in.read() << 16) | (in.read() << 8) | in.read();

            if (type == 0) {
                byte[] block = in.readNBytes(length);
                int sampleRate = (block[10] & 0xFF) << 12 
                               | (block[11] & 0xFF) << 4
                               | ((block[12] & 0xF0) >> 4);

                int channels = ((block[12] & 0x0E) >> 1) + 1;
                int bitDepth = (((block[12] & 1) << 4) | ((block[13] & 0xF0) >> 4)) + 1;

                System.out.println("Sample Rate: " + sampleRate);
                System.out.println("Channels: " + channels);
                System.out.println("Bits Per Sample: " + bitDepth);
            }
        }
    }
}
