import java.util.regex.*;

public class JsonKeys {

    public static void main(String[] args){

        String json="{\"name\":\"Ammaar\",\"lang\":\"Java\"}";

        Matcher m=Pattern.compile("\"(.*?)\"\\s*:").matcher(json);

        while(m.find())
            System.out.println(m.group(1));
    }
}
