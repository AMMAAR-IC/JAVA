import edu.cmu.sphinx.api.*;

public class SpeechToText {
    public static void main(String[] args) throws Exception {
        LiveSpeechRecognizer r = new LiveSpeechRecognizer(Configuration());
        r.startRecognition(true);
        System.out.println("Speak now...");
        String result;
        while ((result = r.getResult().getHypothesis()) != null)
            System.out.println("You said: " + result);
    }
    static Configuration Configuration() {
        Configuration c = new Configuration();
        c.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        c.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        c.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        return c;
    }
}
