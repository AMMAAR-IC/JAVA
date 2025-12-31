public class JsonMinify {
    public static void main(String[] args){
        String json="{   \"name\": \"Ammaar\",\n \"age\":20 }";
        System.out.println(json.replaceAll("\\s+",""));
    }
}
