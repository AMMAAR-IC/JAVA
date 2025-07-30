import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.Scanner;

public class VoiceAIChat {

    public static void speak(String text) {
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin16");
        voice.allocate();
        voice.speak(text);
    }

    public static String getVoiceInput() {
        try {
            Configuration config = new Configuration();
            config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);
            recognizer.startRecognition(true);
            System.out.println("Speak now...");
            String utterance = recognizer.getResult().getHypothesis();
            recognizer.stopRecognition();
            return utterance;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String chatGPTMock(String prompt) {
        // This would be replaced with actual ChatGPT API call
        if (prompt.toLowerCase().contains("hello")) return "Hi! How can I help you?";
        if (prompt.toLowerCase().contains("your name")) return "I'm a Java-powered voice bot.";
        return "Sorry, I didn't understand that.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        speak("Hello. I'm your voice assistant. Ask me anything.");

        while (true) {
            String userInput = getVoiceInput();
            System.out.println("You said: " + userInput);
            String reply = chatGPTMock(userInput);
            System.out.println("Bot: " + reply);
            speak(reply);
        }
    }
}
