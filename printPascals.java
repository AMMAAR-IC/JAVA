public static void printPascals(int n) {
    for (int line = 0; line < n; line++) {
        int num = 1;
        for (int space = 0; space < n - line - 1; space++)
            System.out.print("  ");
        for (int i = 0; i <= line; i++) {
            System.out.printf("%4d", num);
            num = num * (line - i) / (i + 1);
        }
        System.out.println();
    }
}
