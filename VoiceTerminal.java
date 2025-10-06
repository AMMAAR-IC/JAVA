// VoiceTerminal.java
import javax.sound.sampled.*;
import java.io.*;
import java.util.*;

public class VoiceTerminal {
    public static void main(String[] args) throws Exception {
        System.out.println("🎤 Say something (simulated input for demo)...");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();

        if (command.contains("open firefox")) Runtime.getRuntime().exec("firefox");
        else if (command.contains("list files")) Runtime.getRuntime().exec("ls");
        else System.out.println("Unknown command.");
    }
}
