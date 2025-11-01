// TfIdfSearchSimple.java
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class TfIdfSearchSimple {
    List<String> docs = new ArrayList<>();
    List<Map<String,Double>> tfs = new ArrayList<>();
    Map<String,Integer> df = new HashMap<>();
    int n;

    void index(Path folder) throws Exception {
        Files.list(folder).filter(Files::isRegularFile).forEach(p -> {
            try {
                String text = Files.readString(p).toLowerCase();
                docs.add(text);
                String[] tokens = text.split("\\W+");
                Map<String,Double> tf = new HashMap<>();
                for (String t: tokens) if (!t.isBlank()) tf.put(t, tf.getOrDefault(t,0.0)+1);
                tf.replaceAll((k,v)->v / tokens.length);
                for (String k: tf.keySet()) df.put(k, df.getOrDefault(k,0)+1);
                tfs.add(tf);
            } catch (Exception e) {}
        });
        n = docs.size();
    }

    List<Integer> query(String q) {
        String[] tokens = q.toLowerCase().split("\\W+");
        Map<String,Double> qtf = new HashMap<>();
        for (String t: tokens) if (!t.isBlank()) qtf.put(t, qtf.getOrDefault(t,0.0)+1);
        qtf.replaceAll((k,v)->v / tokens.length);

        Map<Integer,Double> scores = new HashMap<>();
        for (int i=0;i<n;i++) {
            double dot=0, normD=0, normQ=0;
            for (var e: qtf.entrySet()) {
                double idf = Math.log((n+1.0)/(1+df.getOrDefault(e.getKey(),0)));
                double qw = e.getValue()*idf;
                double dw = tfs.get(i).getOrDefault(e.getKey(),0.0)*idf;
                dot += qw*dw;
                normQ += qw*qw;
                normD += dw*dw;
            }
            if (dot>0) scores.put(i, dot / (Math.sqrt(normQ)*Math.sqrt(normD)+1e-9));
        }
        return scores.entrySet().stream().sorted((a,b)->Double.compare(b.getValue(),a.getValue()))
            .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception {
        TfIdfSearchSimple s = new TfIdfSearchSimple();
        s.index(Path.of("docs")); // plain .txt files
        System.out.println(s.query("machine learning"));
    }
}
