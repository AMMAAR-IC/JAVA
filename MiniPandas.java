import java.io.*;
import java.util.*;

public class MiniPandas {

    static class DataFrame {
        List<String> columns;
        List<Map<String, String>> rows;

        public DataFrame(String csvPath) throws IOException {
            columns = new ArrayList<>();
            rows = new ArrayList<>();
            loadCSV(csvPath);
        }

        private void loadCSV(String path) throws IOException {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String header = br.readLine();
            if (header == null) throw new IOException("Empty CSV");

            columns = Arrays.asList(header.split(","));

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < columns.size(); i++) {
                    row.put(columns.get(i), values[i]);
                }
                rows.add(row);
            }
            br.close();
        }

        public void head(int n) {
            System.out.println("--- HEAD (" + n + ") ---");
            printRows(0, Math.min(n, rows.size()));
        }

        public void tail(int n) {
            System.out.println("--- TAIL (" + n + ") ---");
            int start = Math.max(0, rows.size() - n);
            printRows(start, rows.size());
        }

        public void shape() {
            System.out.println("Shape: (" + rows.size() + ", " + columns.size() + ")");
        }

        public void filterEquals(String column, String value) {
            System.out.println("--- FILTER: " + column + " == " + value + " ---");
            for (Map<String, String> row : rows) {
                if (row.get(column).equals(value)) {
                    System.out.println(row);
                }
            }
        }

        private void printRows(int start, int end) {
            System.out.println(columns);
            for (int i = start; i < end; i++) {
                System.out.println(rows.get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        String path = scanner.nextLine();

        DataFrame df = new DataFrame(path);

        df.shape();
        df.head(5);
        df.tail(3);

        System.out.print("Enter column to filter: ");
        String col = scanner.nextLine();
        System.out.print("Enter value to match: ");
        String val = scanner.nextLine();
        df.filterEquals(col, val);
    }
}
