package JFrame.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUIScrollBar extends JFrame implements AdjustmentListener {

    private JScrollBar scr[] = new JScrollBar[3];
    private JLabel labels[] = new JLabel[3];
    private Color color;
    int r = 0, g = 0, b = 0;

    public static void main(String[] args) {
        new GUIScrollBar();
    }

    public GUIScrollBar() {
        deployScrollBars();
        deployLabels();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setTitle("Sliders");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(350, 300));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void deployScrollBars() {
        for (int sb = 0; sb < scr.length; sb++) {
            scr[sb] = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
            scr[sb].setPreferredSize(new Dimension(100, 15));
            scr[sb].addAdjustmentListener(this);
            this.add(scr[sb]);
        }

    }

    private void deployLabels() {
        for (int l = 0; l < labels.length; l++) {
            labels[l] = new JLabel("Deslizador " + (l + 1) + ": " + "0", SwingConstants.CENTER);
            this.add(labels[l]);
        }

    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (e.getSource() == scr[0]) {
            r = scr[0].getValue();
            labels[0].setText("Deslizador 1: " + String.valueOf(r));
        }
        if (e.getSource() == scr[1]) {
            g = scr[1].getValue();
            labels[1].setText("Deslizador 2: " + String.valueOf(g));
        }
        if (e.getSource() == scr[2]) {
            b = scr[2].getValue();
            labels[2].setText("Deslizador 3: " + String.valueOf(b));
        }
        color = new Color(r, g, b);
        repaint();
    }

    // Repaint the frame
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillRect(125, 100, 100, 100);
    }

}
