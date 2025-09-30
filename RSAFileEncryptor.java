// RSAFileEncryptor.java
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class RSAFileEncryptor {
    // Generate RSA keypair and save to files
    public static void genKeys() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        try (FileOutputStream fos = new FileOutputStream("private.key")) {
            fos.write(kp.getPrivate().getEncoded());
        }
        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(kp.getPublic().getEncoded());
        }
        System.out.println("Keys generated.");
    }

    // Hybrid encrypt: AES content + RSA encrypt AES key
    public static void encryptFile(String inPath, String outPath) throws Exception {
        byte[] pubBytes = java.nio.file.Files.readAllBytes(new File("public.key").toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(pubBytes);
        PublicKey pub = KeyFactory.getInstance("RSA").generatePublic(spec);

        // generate AES key
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey aes = kg.generateKey();

        // encrypt AES key with RSA
        Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        rsa.init(Cipher.ENCRYPT_MODE, pub);
        byte[] encKey = rsa.doFinal(aes.getEncoded());

        // encrypt file with AES
        Cipher aesC = Cipher.getInstance("AES");
        aesC.init(Cipher.ENCRYPT_MODE, aes);

        try (FileOutputStream fos = new FileOutputStream(outPath);
             CipherOutputStream cos = new CipherOutputStream(fos, aesC);
             FileInputStream fis = new FileInputStream(inPath)) {
            // write RSA-encrypted AES key length + key first
            fos.write(intToBytes(encKey.length));
            fos.write(encKey);
            // then AES-encrypted file content via cos
            byte[] buf = new byte[4096];
            int r;
            while ((r = fis.read(buf)) != -1) cos.write(buf, 0, r);
        }
        System.out.println("Encrypted -> " + outPath);
    }

    public static void decryptFile(String inPath, String outPath) throws Exception {
        byte[] privBytes = java.nio.file.Files.readAllBytes(new File("private.key").toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privBytes);
        PrivateKey priv = KeyFactory.getInstance("RSA").generatePrivate(spec);

        try (FileInputStream fis = new FileInputStream(inPath)) {
            byte[] lenb = new byte[4];
            fis.read(lenb);
            int keyLen = bytesToInt(lenb);
            byte[] encKey = fis.readNBytes(keyLen);

            Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            rsa.init(Cipher.DECRYPT_MODE, priv);
            byte[] aesKey = rsa.doFinal(encKey);
            SecretKeySpec aes = new SecretKeySpec(aesKey, "AES");

            Cipher aesC = Cipher.getInstance("AES");
            aesC.init(Cipher.DECRYPT_MODE, aes);

            try (CipherInputStream cis = new CipherInputStream(fis, aesC);
                 FileOutputStream fos = new FileOutputStream(outPath)) {
                byte[] buf = new byte[4096];
                int r;
                while ((r = cis.read(buf)) != -1) fos.write(buf, 0, r);
            }
        }
        System.out.println("Decrypted -> " + outPath);
    }

    static byte[] intToBytes(int v){ return new byte[]{(byte)(v>>24),(byte)(v>>16),(byte)(v>>8),(byte)v}; }
    static int bytesToInt(byte[] b){ return ((b[0]&0xff)<<24)|((b[1]&0xff)<<16)|((b[2]&0xff)<<8)|(b[3]&0xff); }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) { System.out.println("Usage: gen | encrypt in out | decrypt in out");
