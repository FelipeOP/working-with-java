package JFrame.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Calculator extends JFrame implements ActionListener {

    private double numberOne = 0, numberTwo = 0, result = 0;
    private String operator;
    private Color bg = new Color(35, 35, 35), gray = new Color(19, 19, 19), dark = new Color(6, 6, 6);
    private JTextField resultField, operationField;
    private JPanel buttonContainer;
    private JButton buttons[] = new JButton[20];
    private String buttonsText[] = { "%", "CE", "C", "+",
                                     "1", "2", "3", "-", 
                                     "4", "5", "6", "×", 
                                     "7", "8", "9", "÷",
                                     "±", "0", ".", "=" };

    public static void main(String[] args) {
        new Calculator();
    }

    public Calculator() {
        deployOperationField();
        deployResultField();
        createButtons();
        deployButtonContainer();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(350, 500));
        this.setMinimumSize(new Dimension(350, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(bg);
        this.setResizable(false);
        this.setVisible(true);

    }

    private void deployOperationField() {
        operationField = new JTextField();
        operationField.setPreferredSize(new Dimension(315, 30));
        operationField.setHorizontalAlignment(JTextField.RIGHT);
        operationField.setFont(new Font("Cambria Math", 0, 20));
        operationField.setBackground(bg);
        operationField.setBorder(null);
        operationField.setEditable(false);
        operationField.setForeground(Color.WHITE);
        this.add(operationField);
    }

    private void deployResultField() {
        resultField = new JTextField();
        resultField.setPreferredSize(new Dimension(315, 60));
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setFont(new Font("Cambria Math", 0, 50));
        resultField.setBackground(bg);
        resultField.setBorder(null);
        resultField.setEditable(false);
        resultField.setForeground(Color.WHITE);
        resultField.setText("0");
        this.add(resultField);
    }

    private void createButtons() {
        for (int b = 0; b < buttons.length; b++) {
            buttons[b] = new JButton(buttonsText[b]);
            buttons[b].setFont(new Font("Cambria Math", Font.PLAIN, 30));
            buttons[b].setForeground(Color.WHITE);
            buttons[b].setBackground(dark);
            buttons[b].setBorder(null);
            buttons[b].setFocusable(false);
            buttons[b].addActionListener(this);
        }
        // Row 1 button Background
        for (int fr = 0; fr < 4; fr++) {
            buttons[fr].setBackground(gray);
        }
        // Column 4 button Background
        for (int fc = 3; fc <= 15; fc += 4) {
            buttons[fc].setBackground(gray);
        }
        // Last button
        buttons[buttons.length - 1].setBackground(gray);
    }

    private void deployButtonContainer() {
        buttonContainer = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonContainer.setPreferredSize(new Dimension(315, 330));
        buttonContainer.setBackground(bg);
        // buttonContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        for (int b = 0; b < buttons.length; b++) {
            buttonContainer.add(buttons[b]);
        }
        this.add(buttonContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (resultField.getText().equals("0")) {
            resultField.setText("");
        }

        if (isNumber(e.getActionCommand())) {
            // Delete all and start other operation
            if (operationField.getText().contains("=")) {
                resultField.setText("");
                operationField.setText("");
            }
            // Concatenate only numbers
            resultField.setText(resultField.getText().concat(e.getActionCommand()));

        } // Only one decimal dot
        else if (e.getActionCommand().equals(".")) {
            if (!resultField.getText().contains(".")) {
                resultField.setText(resultField.getText().concat(e.getActionCommand()));
            }
        } // any sign except "="
        else if (!e.getActionCommand().equals("=")) {
            String tempOp = String.valueOf(operator);
            operator = e.getActionCommand();
            defineOperation();
            if (e.getActionCommand() == "CE" || e.getActionCommand() == "±") {
                operator = tempOp;
            }
        } else {
            perfomOperation();
        }

    }

    private boolean isNumber(String number) {
        return number.matches("[0-9]{1,}") ? true : false;
    }

    private void defineOperation() {
        // Delete all
        if (operator.equals("C")) {
            resultField.setText("");
            operationField.setText("");
            numberOne = numberTwo = result = 0;
        }
        // Special operators
        if (!resultField.getText().isEmpty()) {
            switch (operator) {
                case "CE":
                    StringBuffer rf = new StringBuffer(resultField.getText());
                    rf.deleteCharAt(rf.length() - 1);
                    resultField.setText(rf.toString());
                    break;
                case "±":
                    resultField.setText(String.valueOf(Double.valueOf(resultField.getText()) * (-1)));
                    break;
                default:
                    numberOne = Double.parseDouble(resultField.getText());
                    operationField.setText(resultField.getText() + " " + operator + " ");
                    resultField.setText("");
                    break;
            }
        }
    }

    private void perfomOperation() {
        if (resultField.getText().isEmpty()) {
            numberTwo = 0;
        } else {
            numberTwo = Double.parseDouble(resultField.getText());
        }
        if (operator != null) {
            switch (operator) {
                case "%":
                    result = (numberOne) % (numberTwo);
                    break;
                case "+":
                    result = (numberOne) + (numberTwo);
                    break;
                case "-":
                    result = (numberOne) - (numberTwo);
                    break;
                case "×":
                    result = (numberOne) * (numberTwo);
                    break;
                case "÷":
                    result = (numberOne) / (numberTwo);
                    break;
            }
        }
        // Don't text repeat in operation field and format negative numbers
        if (!operationField.getText().contains("=")) {
            if (numberTwo < 0) {
                operationField.setText(operationField.getText().concat(
                        "(" + String.valueOf(numberTwo) + ")" + " = " + String.format(Locale.ENGLISH, "%.2f", result)));
            } else {
                operationField.setText(operationField.getText()
                        .concat(String.valueOf(numberTwo) + " = " + String.format(Locale.ENGLISH, "%.2f", result)));
            }
            numberOne = result;
            resultField.setText(String.format(Locale.ENGLISH, "%.2f", numberOne));
        }
    }
}
