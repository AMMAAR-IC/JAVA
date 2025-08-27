import java.nio.file.*; import java.security.*; 

public class FileSHA {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) { System.out.println("Usage: java FileSHA <file>"); return; }
        byte[] data = Files.readAllBytes(Path.of(args[0]));
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data);
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) hex.append(String.format("%02x", b));
        System.out.println(args[0] + " => " + hex);
    }
}
