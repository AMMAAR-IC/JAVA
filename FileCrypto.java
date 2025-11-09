import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;

public class FileCrypto {
    private static SecretKey getKey(String password, byte[] salt) throws Exception {
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        return new SecretKeySpec(f.generateSecret(spec).getEncoded(), "AES");
    }

    public static void encrypt(String inFile, String outFile, String password) throws Exception {
        byte[] salt = "12345678".getBytes();
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey key = getKey(password, salt);
        c.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        try (FileInputStream in = new FileInputStream(inFile);
             FileOutputStream out = new FileOutputStream(outFile)) {
            out.write(c.doFinal(in.readAllBytes()));
        }
    }

    public static void decrypt(String inFile, String outFile, String password) throws Exception {
        byte[] salt = "12345678".getBytes();
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey key = getKey(password, salt);
        c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        try (FileInputStream in = new FileInputStream(inFile);
             FileOutputStream out = new FileOutputStream(outFile)) {
            out.write(c.doFinal(in.readAllBytes()));
        }
    }

    public static void main(String[] args) throws Exception {
        encrypt("plain.txt", "enc.dat", "MyPass");
        decrypt("enc.dat", "dec.txt", "MyPass");
        System.out.println("✅ Done!");
    }
}
