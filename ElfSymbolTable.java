import java.io.*;
import java.nio.charset.StandardCharsets;

public class ElfSymbolTable {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("a.out", "r");

        raf.seek(0x18);
        long shoff = Long.reverseBytes(raf.readLong());
        raf.seek(0x3A);
        int shentsize = Short.reverseBytes(raf.readShort());
        int shnum = Short.reverseBytes(raf.readShort());
        int shstr = Short.reverseBytes(raf.readShort());

        long shstrOffset = shoff + shstr * shentsize;
        raf.seek(shstrOffset + 0x18);
        long shstrData = Long.reverseBytes(raf.readLong());

        long symtabOff = 0, symtabSize = 0, strtabOff = 0;
        for (int i = 0; i < shnum; i++) {
            long off = shoff + i * shentsize;
            raf.seek(off + 0x04);
            int type = Integer.reverseBytes(raf.readInt());
            raf.seek(off + 0x18);
            long ptr = Long.reverseBytes(raf.readLong());
            long size = Long.reverseBytes(raf.readLong());

            if (type == 2) { symtabOff = ptr; symtabSize = size; }
            if (type == 3) { strtabOff = ptr; }
        }

        System.out.println("Symbols:");
        for (long p = 0; p < symtabSize; p += 24) {
            raf.seek(symtabOff + p);
            int nameOff = Integer.reverseBytes(raf.readInt());
            raf.seek(strtabOff + nameOff);
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = raf.read()) > 0) sb.append((char) c);
            if (sb.length() > 0) System.out.println(" - " + sb);
        }
    }
}
