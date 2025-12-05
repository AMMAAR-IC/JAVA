import java.io.*;

public class Mp4AtomViewer {
    public static void main(String[] args) throws Exception {
        try (var in = new DataInputStream(new FileInputStream("video.mp4"))) {
            while (in.available() > 8) {
                int size = in.readInt();
                byte[] typ = in.readNBytes(4);
                String atom = new String(typ);

                System.out.printf("Atom: %s (%d bytes)\n", atom, size);

                if (size < 8) break; 
                in.skipBytes(size - 8);
            }
        }
    }
}
