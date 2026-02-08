import java.util.regex.*;

public class HtmlURLExtractor {
    public static void main(String[] args){
        String html="<a href='https://google.com'>G</a><a href=\"/home\">H</a>";
        Matcher m=Pattern.compile("href=[\"'](.*?)[\"']").matcher(html);

        while(m.find())
            System.out.println(m.group(1));
    }
}
