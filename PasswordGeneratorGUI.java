// PasswordGeneratorGUI.java
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PasswordGeneratorGUI {
    public static void main(String[] args) {
        JFrame f = new JFrame("Password Generator");
        JTextField field = new JTextField();
        JButton generate = new JButton("Generate");

        generate.addActionListener(e -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
            Random r = new Random();
            StringBuilder pass = new StringBuilder();
            for (int i = 0; i < 12; i++)
                pass.append(chars.charAt(r.nextInt(chars.length())));
            field.setText(pass.toString());
        });

        f.add(field, BorderLayout.CENTER);
        f.add(generate, BorderLayout.SOUTH);
        f.setSize(300, 100);
        f.setVisible(true);
    }
}
