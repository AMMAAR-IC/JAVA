import java.io.*;
import java.nio.file.*;

public class Sha1Pure {
    public static void main(String[] args) throws Exception {
        byte[] message = Files.readAllBytes(Path.of("file.bin"));
        System.out.println(bytesToHex(sha1(message)));
    }

    static byte[] sha1(byte[] b) {
        int h0 = 0x67452301, h1 = 0xEFCDAB89, h2 = 0x98BADCFE,
            h3 = 0x10325476, h4 = 0xC3D2E1F0;

        int origLen = b.length * 8;
        int newLen = ((b.length + 8) / 64 + 1) * 64;
        byte[] msg = new byte[newLen];
        System.arraycopy(b, 0, msg, 0, b.length);
        msg[b.length] = (byte) 0x80;
        for (int i=56;i<64;i++) msg[newLen-64 + i] = (byte)((origLen >>> (8*(63-i))) & 0xFF);

        for (int i=0; i<msg.length; i+=64) {
            int[] w = new int[80];
            for (int j=0;j<16;j++)
                w[j] = ((msg[i+j*4] & 0xFF) << 24)
                     | ((msg[i+j*4+1] & 0xFF) << 16)
                     | ((msg[i+j*4+2] & 0xFF) << 8)
                     | (msg[i+j*4+3] & 0xFF);

            for (int j=16;j<80;j++)
                w[j] = Integer.rotateLeft(w[j-3] ^ w[j-8] ^ w[j-14] ^ w[j-16], 1);

            int a=h0,b1=h1,c=h2,d=h3,e=h4;

            for (int j=0;j<80;j++) {
                int f,k;
                if (j<20){f=(b1&c)|((~b1)&d); k=0x5A827999;}
                else if (j<40){f=b1^c^d; k=0x6ED9EBA1;}
                else if (j<60){f=(b1&c)|(b1&d)|(c&d); k=0x8F1BBCDC;}
                else {f=b1^c^d; k=0xCA62C1D6;}
                int temp = Integer.rotateLeft(a,5) + f + e + k + w[j];
                e=d; d=c; c=Integer.rotateLeft(b1,30); b1=a; a=temp;
            }

            h0 += a; h1 += b1; h2 += c; h3 += d; h4 += e;
        }

        return new byte[] {
            (byte)(h0>>24),(byte)(h0>>16),(byte)(h0>>8),(byte)h0,
            (byte)(h1>>24),(byte)(h1>>16),(byte)(h1>>8),(byte)h1,
            (byte)(h2>>24),(byte)(h2>>16),(byte)(h2>>8),(byte)h2,
            (byte)(h3>>24),(byte)(h3>>16),(byte)(h3>>8),(byte)h3,
            (byte)(h4>>24),(byte)(h4>>16),(byte)(h4>>8),(byte)h4
        };
    }

    static String bytesToHex(byte[] b){
        StringBuilder sb = new StringBuilder();
        for(byte x:b) sb.append(String.format("%02x", x));
        return sb.toString();
    }
}
