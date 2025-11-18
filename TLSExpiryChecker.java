// TLSExpiryChecker.java
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.net.URL;

public class TLSExpiryChecker {
    public static void main(String[] args) throws Exception {
        String host = args.length>0 ? args[0] : "github.com";
        HttpsURLConnection conn = (HttpsURLConnection) new URL("https://" + host).openConnection();
        conn.connect();
        System.out.println("Connected to: " + host);
        for (java.security.cert.Certificate cert : conn.getServerCertificates()) {
            if (cert instanceof X509Certificate) {
                X509Certificate x = (X509Certificate) cert;
                System.out.println("Subject: " + x.getSubjectDN());
                System.out.println("Issuer:  " + x.getIssuerDN());
                System.out.println("Valid from: " + x.getNotBefore() + " to " + x.getNotAfter());
                System.out.println("Days until expiry: " + ((x.getNotAfter().getTime() - System.currentTimeMillis()) / (1000L*60*60*24)));
                System.out.println("----");
            }
        }
        conn.disconnect();
    }
}
