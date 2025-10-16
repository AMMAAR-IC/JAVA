import com.sun.speech.freetts.*;

public class TextToSpeech {
    public static void main(String[] args) {
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak("Hello Ammar! Welcome to your Java repo.");
        }
    }
}
