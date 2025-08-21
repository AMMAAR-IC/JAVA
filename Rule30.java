public class Rule30 {
    public static void main(String[] args) {
        int size = 61;
        boolean[] row = new boolean[size];
        row[size / 2] = true; // single "seed"

        for (int gen = 0; gen < 30; gen++) {
            for (boolean cell : row)
                System.out.print(cell ? "█" : " ");
            System.out.println();

            boolean[] newRow = new boolean[size];
            for (int i = 1; i < size - 1; i++) {
                boolean left = row[i - 1], mid = row[i], right = row[i + 1];
                newRow[i] = (left ^ (mid || right)); // Rule 30 logic
            }
            row = newRow;
        }
    }
}
