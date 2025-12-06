import java.io.*;

public class JpegQuantTables {
    public static void main(String[] args) throws Exception {
        DataInputStream in = new DataInputStream(new FileInputStream("image.jpg"));

        while (in.available() > 0) {
            if (in.readUnsignedByte() != 0xFF) continue;
            int marker = in.readUnsignedByte();
            int size = in.readUnsignedShort() - 2;

            if (marker == 0xDB) { // DQT
                System.out.println("Found DQT segment (" + size + " bytes)");
                int pqTq = in.readUnsignedByte();
                int[] table = new int[64];
                for (int i=0;i<64;i++) table[i] = in.readUnsignedByte();
                for (int i=0;i<8;i++) {
                    for (int j=0;j<8;j++) System.out.printf("%3d ", table[i*8+j]);
                    System.out.println();
                }
                break;
            } else in.skipBytes(size);
        }
    }
}
