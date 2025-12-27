import javax.sound.sampled.*;

public class ToneGen {
    public static void main(String[] a)throws Exception{
        byte[] buf=new byte[44100];
        for(int i=0;i<buf.length;i++)
            buf[i]=(byte)((i/50)%2==0?127:-127);

        AudioFormat f=new AudioFormat(44100,8,1,true,false);
        SourceDataLine s=AudioSystem.getSourceDataLine(f);
        s.open(f); s.start(); s.write(buf,0,buf.length);
    }
}
