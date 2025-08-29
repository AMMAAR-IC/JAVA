public class PalindromeSentence {
    public static void main(String[] args) {
        String text = "A man a plan a canal Panama";
        String clean = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String rev = new StringBuilder(clean).reverse().toString();
        System.out.println(text+" → "+clean.equals(rev));
    }
}
