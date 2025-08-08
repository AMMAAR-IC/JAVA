import javax.speech.*;
import javax.speech.recognition.*;
import java.util.Locale;

public class VoiceLogger extends ResultAdapter {
    public void resultAccepted(ResultEvent e) {
        try {
            Result r = (Result)(e.getSource());
            ResultToken[] tokens = r.getBestTokens();
            for (ResultToken token : tokens)
                System.out.print(token.getSpokenText() + " ");
            System.out.println();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public static void main(String[] args) throws Exception {
        Recognizer rec = Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH));
        rec.allocate();
        rec.addResultListener(new VoiceLogger());
        rec.commitChanges();
        rec.requestFocus();
        rec.resume();
    }
}
