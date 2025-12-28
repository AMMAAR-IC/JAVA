public class StripHTML {
    public static void main(String[] args){
        String html="<h1>Hello <b>Ammaar</b></h1>";
        System.out.println(html.replaceAll("<[^>]*>",""));
    }
}
