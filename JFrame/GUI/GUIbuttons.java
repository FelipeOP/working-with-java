package JFrame.GUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.Locale;
import java.awt.*;

public class GUIbuttons extends JFrame implements ActionListener {
    
    double numberOne = 0, numberTwo = 0, result = 0;
    JLabel numberLabels[] = new JLabel[3];
    JTextField numberTextFields[] = new JTextField[3];
    JButton buttons[] = new JButton[8];
    String buttonsText[] = { "Sumar", "Restar", "Multiplicar", "Dividir", "Raiz de 1", "Raiz de 2", "Mayor", "Limpiar" };
    public static void main(String[] args) {
        new GUIbuttons();
    }

    public GUIbuttons() {
        deployComponents();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setTitle("Buttons");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(250, 350));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void deployComponents() {
        for (int lt = 0; lt < numberLabels.length; lt++) {
            numberLabels[lt] = new JLabel();
            numberTextFields[lt] = new JTextField(10);
        }
        numberLabels[0].setText("Numero 1:");
        numberLabels[1].setText("Numero 2:");
        numberLabels[2].setText("Resultado: ");
        // Add Labels and Textfields
        this.add(numberLabels[0]);
        this.add(numberTextFields[0]);
        this.add(numberLabels[1]);
        this.add(numberTextFields[1]);
        // Add buttons
        for (int b = 0; b < buttons.length; b++) {
            buttons[b] = new JButton(buttonsText[b]);
            buttons[b].addActionListener(this);
            this.add(buttons[b]);
        }
        // Result Fields
        this.add(numberLabels[2]);
        this.add(numberTextFields[2]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parseNumbers();
        perfomOperation(e.getActionCommand());
    }

    private void perfomOperation(String actionCommand) {
        switch (actionCommand) {
            case "Sumar":
                result = numberOne + numberTwo;
                break;
            case "Restar":
                result = numberOne - numberTwo;
                break;
            case "Multiplicar":
                result = numberOne * numberTwo;
                break;
            case "Dividir":
                result = numberOne / numberTwo;
                break;
            case "Raiz de 1":
                result = Math.sqrt(numberOne);
                break;
            case "Raiz de 2":
                result = Math.sqrt(numberTwo);
                break;
            case "Mayor":
                result = Math.max(numberOne, numberTwo);
                break;
        }
        numberTextFields[2].setText(String.format(Locale.US, "%.2f", result));
        if (actionCommand.equals("Limpiar")) {
            clearAll();
        }
    }

    private void clearAll() {
        numberOne = numberTwo = result = 0;
        for (int t = 0; t < numberTextFields.length; t++) {
            numberTextFields[t].setText("");
        }
    }

    private void parseNumbers() {
        if (!numberTextFields[0].getText().isEmpty()) {
            numberOne = Double.parseDouble(numberTextFields[0].getText());
        }
        if (!numberTextFields[1].getText().isEmpty()) {
            numberTwo = Double.parseDouble(numberTextFields[1].getText());
        }
    }
}
