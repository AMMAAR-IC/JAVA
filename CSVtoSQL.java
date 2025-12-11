import java.nio.file.*;

public class CSVtoSQL {
    public static void main(String[] args) throws Exception {
        String[] lines = Files.readString(Path.of("data.csv")).split("\n");
        String table = "my_table";

        for (String l : lines) {
            String[] cols = l.split(",");
            System.out.println(
                "INSERT INTO " + table + " VALUES ('" +
                String.join("','", cols) + "');"
            );
        }
    }
}
