import java.io.FileInputStream;
import java.security.MessageDigest;

public class ImageHashCheck {
    public static String getFileHash(String path) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }
        fis.close();

        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String originalHash = getFileHash("original.jpg");
        String suspectHash = getFileHash("suspicious.jpg");

        System.out.println("Original Hash:  " + originalHash);
        System.out.println("Suspicious Hash: " + suspectHash);

        if (originalHash.equals(suspectHash)) {
            System.out.println("✅ Image not tampered.");
        } else {
            System.out.println("⚠️ Image modified / forged.");
        }
    }
}
