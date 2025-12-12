public class JsonMinifier {
    public static void main(String[] args) {
        String json = "{ \"name\": \"Ammaar\",  \"age\" : 20 }";
        System.out.println(minify(json));
    }

    static String minify(String s){
        StringBuilder out = new StringBuilder();
        boolean inStr = false;

        for(char c : s.toCharArray()){
            if(c=='"') inStr = !inStr;
            if(!inStr && Character.isWhitespace(c)) continue;
            out.append(c);
        }
        return out.toString();
    }
}
