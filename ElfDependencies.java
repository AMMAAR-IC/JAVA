import java.io.*;

public class ElfDependencies {
    public static void main(String[] args) throws Exception {
        RandomAccessFile f = new RandomAccessFile("a.out","r");

        f.seek(0x18);
        long phoff = Long.reverseBytes(f.readLong());

        f.seek(0x36);
        short phentsize = Short.reverseBytes(f.readShort());
        short phnum = Short.reverseBytes(f.readShort());

        long dynOff=0, dynSize=0;
        for (int i=0;i<phnum;i++){
            long off = phoff + i*phentsize;
            f.seek(off);
            int type = Integer.reverseBytes(f.readInt());
            f.skipBytes(4);
            long pOffset = Long.reverseBytes(f.readLong());
            long pFilesz = Long.reverseBytes(f.readLong());

            if (type == 2) { dynOff=pOffset; dynSize=pFilesz; }
        }

        f.seek(dynOff);
        long strTab=0;

        System.out.println("Dependencies:");
        for (long p=0;p<dynSize;p+=16){
            f.seek(dynOff+p);
            long tag = Long.reverseBytes(f.readLong());
            long val = Long.reverseBytes(f.readLong());

            if (tag == 5) strTab = val;
        }

        for (long p=0;p<dynSize;p+=16){
            f.seek(dynOff+p);
            long tag = Long.reverseBytes(f.readLong());
            long val = Long.reverseBytes(f.readLong());
            if (tag == 1) {
                f.seek(strTab + val);
                System.out.println("  • " + readStr(f));
            }
        }
    }

    static String readStr(RandomAccessFile f) throws Exception {
        StringBuilder sb = new StringBuilder();
        int c; while ((c=f.read())>0) sb.append((char)c);
        return sb.toString();
    }
}
