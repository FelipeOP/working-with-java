package JFrame.GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIColors extends JFrame implements ActionListener {
    
    private JComboBox<String> jComboBox;

    public static void main(String[] args) {
        new GUIColors();
    }

    public GUIColors() {
        createList();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new FlowLayout());
        this.setTitle("Interfaz");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
    }

    public void createList() {
        String colors[] = { "Red", "Blue", "Yellow", "Black", "Green" };
        jComboBox = new JComboBox<String>(colors);
        jComboBox.addActionListener(this);
        this.add(jComboBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics2D g2D = (Graphics2D) getGraphics();
        if (e.getSource().equals(jComboBox)) {
            switch (jComboBox.getSelectedItem().toString()) {
                case "Red":
                    g2D.setColor(Color.red);
                    break;
                case "Blue":
                    g2D.setColor(Color.blue);
                    break;
                case "Yellow":
                    g2D.setColor(Color.yellow);
                    break;
                case "Black":
                    g2D.setColor(Color.black);
                    break;
                case "Green":
                    g2D.setColor(Color.green);
                    break;
            }
            int dx = this.getWidth() / 2 - 50;
            int dy = this.getHeight() / 2 - 50;
            g2D.fillRect(dx, dy, 100, 100);
        }
    }

}
