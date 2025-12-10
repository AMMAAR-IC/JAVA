import javax.net.ssl.*;
import java.net.*;
import java.security.cert.*;

public class SSLCertExpiry {
    public static void main(String[] args) throws Exception {
        String host = "google.com";
        int port = 443;

        SSLSocket s = (SSLSocket) SSLSocketFactory.getDefault().createSocket(host, port);
        s.startHandshake();

        X509Certificate cert = (X509Certificate) s.getSession().getPeerCertificates()[0];
        System.out.println("Issuer: " + cert.getIssuerDN());
        System.out.println("Expires: " + cert.getNotAfter());
    }
}
