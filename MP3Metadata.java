import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;

public class MP3Metadata {
    public static void main(String[] args) {
        try {
            File file = new File("song.mp3");
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();

            System.out.println("Title: " + tag.getFirst(org.jaudiotagger.tag.FieldKey.TITLE));
            System.out.println("Artist: " + tag.getFirst(org.jaudiotagger.tag.FieldKey.ARTIST));
            System.out.println("Album: " + tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM));
            System.out.println("Year: " + tag.getFirst(org.jaudiotagger.tag.FieldKey.YEAR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
