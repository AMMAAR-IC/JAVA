import javax.sound.sampled.*;
import java.io.*;

public class VoiceRecorder {
    public static void main(String[] args) throws Exception {
        AudioFormat format = new AudioFormat(16000, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);

        microphone.open(format);
        microphone.start();

        File file = new File("record.wav");
        AudioInputStream ais = new AudioInputStream(microphone);
        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);

        System.out.println("🎙️ Recording... Press Ctrl+C to stop.");
    }
}
