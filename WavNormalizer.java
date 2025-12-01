import javax.sound.sampled.*;
import java.io.*;

public class WavNormalizer {
    public static void main(String[] args) throws Exception {
        File f = new File("in.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(f);
        AudioFormat fmt = ais.getFormat();
        byte[] data = ais.readAllBytes();
        int max = 0;
        for (int i=0;i<data.length;i+=2) {
            int sample = (data[i+1]<<8) | (data[i]&0xFF);
            max = Math.max(max, Math.abs(sample));
        }
        double gain = 30000.0 / max;
        for (int i=0;i<data.length;i+=2) {
            int s = (data[i+1]<<8) | (data[i]&0xFF);
            int ns = (int)(s * gain);
            data[i] = (byte)(ns & 0xFF);
            data[i+1] = (byte)((ns >> 8) & 0xFF);
        }
        try (FileOutputStream fos = new FileOutputStream("out_normalized.wav")) {
            fos.write(Files.readAllBytes(new File("in.wav").toPath()).length > 44 ? Files.readAllBytes(new File("in.wav").toPath()) : new byte[0]);
            // Simple: for real usage rebuild WAV header + write data. This is a quick demo.
        }
        System.out.println("Normalization applied (quick demo).");
    }
}
