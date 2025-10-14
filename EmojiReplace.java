// EmojiReplace.java
import java.nio.file.*;
import java.util.*;

public class EmojiReplace {
    public static void main(String[] args) throws Exception {
        String t = Files.readString(Path.of("chat.txt"));
        Map<String,String> map = Map.of(":)", "😊", ":(", "☹️", "<3", "❤️");
        for (var e : map.entrySet()) t = t.replace(e.getKey(), e.getValue());
        Files.writeString(Path.of("chat_emoji.txt"), t);
        System.out.println("Saved chat_emoji.txt");
    }
}
