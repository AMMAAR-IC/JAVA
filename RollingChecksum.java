public class RollingChecksum {
    static int checksum(byte[] b){
        int a=1, c=0;
        for(byte x:b){
            a = (a + (x & 255)) % 65521;
            c = (c + a) % 65521;
        }
        return (c << 16) | a;
    }

    public static void main(String[] args){
        System.out.println(checksum("hello".getBytes()));
    }
}
