import java.io.*;
import javax.sound.sampled.*;

public class SineWaveGen {
    public static void main(String[] args) throws Exception {
        int rate=44100, seconds=2;
        byte[] data = new byte[rate*seconds*2];
        double freq = 440.0;
        for (int i=0;i<data.length/2;i++) {
            short val = (short)(Math.sin(2*Math.PI*freq*i/rate)*32767);
            data[i*2]=(byte)(val & 0xff);
            data[i*2+1]=(byte)(val>>8);
        }
        AudioFormat fmt = new AudioFormat(rate,16,1,true,false);
        AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(data),fmt,data.length/2),
                          AudioFileFormat.Type.WAVE,new File("tone.wav"));
        System.out.println("tone.wav created.");
    }
}
