package JFrame.GUI;

import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIHandlers extends JFrame implements ActionListener {
    
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    private JPanel topPanel, botPanel;
    private JTextField textFields[] = new JTextField[3];
    private JLabel labels[] = new JLabel[3];
    private JButton buttons[] = new JButton[9];
    private double numberX = 0, numberY = 0, result = 0;

    public static void main(String[] args) {
        new GUIHandlers();
    }

    public GUIHandlers() {
        deployTopPanel();
        deployBottomPanel();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new GridLayout(2, 1, 10, 5));
        this.setTitle("Handlers");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 300));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
    }

    private void deployTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        topPanel.setBackground(Color.white);
        // Labels
        for (int lt = 0; lt < labels.length; lt++) {
            labels[lt] = new JLabel("", JLabel.CENTER);
            labels[lt].setBorder(border);
            topPanel.add(labels[lt]);
            textFields[lt] = new JTextField();
            textFields[lt].setBorder(border);
            textFields[lt].setHorizontalAlignment(JTextField.CENTER);
        }
        labels[0].setText("X");
        labels[1].setText("Y");
        labels[2].setText("Resultado");
        // Textfields
        for (int t = 0; t < textFields.length; t++) {
            topPanel.add(textFields[t]);
        }
        textFields[2].setEditable(false);
        this.add(topPanel);
    }

    private void deployBottomPanel() {
        botPanel = new JPanel(new GridLayout(3, 3, 10, 5));
        botPanel.setBackground(Color.white);
        for (int b = 0; b < buttons.length; b++) {
            buttons[b] = new JButton();
            buttons[b].setHorizontalTextPosition(JButton.CENTER);
            buttons[b].addActionListener(this);
            buttons[b].setFocusable(false);
            buttons[b].setBorder(border);
            botPanel.add(buttons[b]);
        }
        // Row 1
        buttons[0].setText("+");
        buttons[1].setText("-");
        buttons[2].setText("*");
        // Row 2
        buttons[3].setText("/");
        buttons[4].setText("X½");
        buttons[5].setText("Y½");
        // Row 3
        buttons[6].setText("X^Y");
        buttons[7].setText("Y^X");
        buttons[8].setText("X%Y");
        this.add(botPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        verifyTextFields();
        switch (e.getActionCommand()) {
            case "+":
                result = numberX + numberY;
                break;
            case "-":
                result = numberX - numberY;
                break;
            case "*":
                result = numberX * numberY;
                break;
            case "/":
                result = numberX / numberY;
                break;
            case "X½":
                result = Math.sqrt(numberX);
                break;
            case "Y½":
                result = Math.sqrt(numberY);
                break;
            case "X^Y":
                result = Math.pow(numberX, numberY);
                break;
            case "Y^X":
                result = Math.pow(numberY, numberX);
                break;
            case "X%Y":
                result = numberX % numberY;
                break;
            default:
                break;
        }
        textFields[2].setText(String.format("%.2f", result));

    }

    private void verifyTextFields() {
        if (!textFields[0].getText().isEmpty()) {
            numberX = Double.parseDouble(textFields[0].getText());
        } else {
            textFields[0].setText("0");
            numberX = 0;
        }
        if (!textFields[1].getText().isEmpty()) {
            numberY = Double.parseDouble(textFields[1].getText());
        } else {
            textFields[1].setText("0");
            numberY = 0;
        }
    }

}
