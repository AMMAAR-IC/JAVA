import java.io.*;

public class HeicInfo {
    public static void main(String[] args) throws Exception {
        DataInputStream in = new DataInputStream(new FileInputStream("image.heic"));

        while (in.available() > 8) {
            int size = in.readInt();
            byte[] type = in.readNBytes(4);
            String atom = new String(type);

            System.out.println("Box: " + atom + " (" + size + " bytes)");

            if (atom.equals("ftyp")) {
                byte[] brand = in.readNBytes(4);
                System.out.println("Brand: " + new String(brand));
                in.skipBytes(size - 12);
            } else if (atom.equals("meta")) {
                System.out.println("Found metadata box");
                in.skipBytes(size - 8);
            } else {
                in.skipBytes(size - 8);
            }
        }
    }
}
