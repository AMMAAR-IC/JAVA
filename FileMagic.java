import java.nio.file.*;

public class FileMagic {
    public static void main(String[] args) throws Exception {
        byte[] b = Files.readAllBytes(Path.of("unknown.bin"));

        if (starts(b,new byte[]{0x50,0x4B})) System.out.println("ZIP");
        else if (starts(b,new byte[]{(byte)0xFF,(byte)0xD8})) System.out.println("JPEG");
        else if (starts(b,new byte[]{0x89,0x50,0x4E,0x47})) System.out.println("PNG");
        else System.out.println("Unknown file");
    }

    static boolean starts(byte[] f, byte[] sig){
        for(int i=0;i<sig.length;i++)
            if(f[i]!=sig[i]) return false;
        return true;
    }
}
