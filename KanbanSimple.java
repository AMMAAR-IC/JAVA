// KanbanSimple.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KanbanSimple {
    public static void main(String[] args) {
        JFrame f = new JFrame("Kanban");
        JPanel todo = column("To Do"), doing = column("Doing"), done = column("Done");
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(todo), new JScrollPane(doing));
        sp.setRightComponent(new JScrollPane(done));
        f.add(sp);
        f.setSize(800,400); f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.setVisible(true);

        JButton add = new JButton("Add Task");
        add.addActionListener(e -> todo.add(card("New Task")));
        f.add(add, BorderLayout.NORTH);
    }

    static JPanel column(String name) {
        JPanel p = new JPanel(); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); p.setBorder(BorderFactory.createTitledBorder(name));
        return p;
    }

    static JPanel card(String text) {
        JPanel c = new JPanel(); c.add(new JLabel(text));
        c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.setMaximumSize(new Dimension(200,50));
        c.addMouseListener(new MouseAdapter(){ public void mousePressed(MouseEvent e){ ((JComponent)e.getSource()).getParent().remove((Component)e.getSource()); ((JComponent)e.getSource()).revalidate(); }});
        return c;
    }
}
