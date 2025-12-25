import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MiniJWT {
    public static void main(String[] args) throws Exception {
        String header = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"HS256\"}".getBytes());

        String payload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"user\":\"ammaar\"}".getBytes());

        String sig = sign(header + "." + payload, "secret");
        System.out.println(header + "." + payload + "." + sig);
    }

    static String sign(String data,String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(),"HmacSHA256"));
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(mac.doFinal(data.getBytes()));
    }
}
