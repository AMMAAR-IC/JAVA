// WikiSearch.java
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class WikiSearch {
    public static void main(String[] args) {
        JFrame f = new JFrame("Wiki Search");
        JTextField search = new JTextField();
        JTextArea result = new JTextArea();
        JButton btn = new JButton("Search");

        btn.addActionListener(e -> {
            try {
                String query = URLEncoder.encode(search.getText(), "UTF-8");
                URL url = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/" + query);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String line, json = "";
                while ((line = br.readLine()) != null) json += line;
                int start = json.indexOf("\"extract\":\"") + 11;
                int end = json.indexOf("\",\"", start);
                result.setText(json.substring(start, end));
            } catch (Exception ex) {
                result.setText("Error fetching data!");
            }
        });

        f.setLayout(new BorderLayout());
        f.add(search, BorderLayout.NORTH);
        f.add(new JScrollPane(result), BorderLayout.CENTER);
        f.add(btn, BorderLayout.SOUTH);
        f.setSize(400, 300);
        f.setVisible(true);
    }
}
