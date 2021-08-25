package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

public class GUIArray1D extends JFrame implements ActionListener {

    final private int ARRAYSIZE = 10;
    private int array1D[] = new int[ARRAYSIZE];
    private String buttonsText[] = { "Agregar", "Borrar", "Limpiar", "Consultar", "Promedio", "Aleatorio" };
    private String labelsText[] = { "Numero a ingresar:", "Posición:", "Resutado:" };
    private JButton buttons[] = new JButton[buttonsText.length];
    private JLabel labels[] = new JLabel[labelsText.length];
    private JTextField number, position, result;

    public static void main(String[] args) {
        new GUIArray1D();
    }

    public GUIArray1D() {
        instanceLabels();
        instanceButtons();
        instanceTextFields();
        deployComponents();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new GridLayout(6, 2, 20, 20));
        this.setTitle("One-Dimensional Arrays");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
    }

    private void deployComponents() {
        this.add(labels[0]);
        this.add(number);
        this.add(labels[1]);
        this.add(position);
        this.add(labels[2]);
        this.add(result);
        for (int b = 0; b < buttons.length; b++) {
            this.add(buttons[b]);
        }
    }

    private void instanceTextFields() {
        number = new JTextField();
        position = new JTextField();
        result = new JTextField();
        result.setEditable(false);
    }

    private void instanceButtons() {
        for (int b = 0; b < buttonsText.length; b++) {
            buttons[b] = new JButton();
            buttons[b].setText(buttonsText[b]);
            buttons[b].addActionListener(this);
            buttons[b].setFocusable(false);
        }
    }

    private void instanceLabels() {
        for (int i = 0; i < labelsText.length; i++) {
            labels[i] = new JLabel(labelsText[i]);
            labels[i].setHorizontalAlignment(JLabel.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        performOperation(e.getActionCommand());
    }

    private void performOperation(String actionCommand) {
        switch (actionCommand) {
            case "Agregar":
                addElement();
                break;
            case "Borrar":
                eraseElement();
                break;
            case "Limpiar":
                clearArray();
                break;
            case "Consultar":
                printArray();
                break;
            case "Promedio":
                average();
                break;
            case "Aleatorio":
                randomFill();
                break;
            default:
                System.out.println("Acción invalida o desconocida");
                break;
        }
    }

    private void addElement() {
        try {
            if (validIndex()) {
                int index = Integer.parseInt(position.getText());
                int num = Integer.parseInt(number.getText());
                array1D[index] = num;
                printArray();
            } else {
                throw new IndexOutOfBoundsException("Posición Invalida");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void eraseElement() {
        try {
            if (validIndex()) {
                int index = Integer.parseInt(position.getText());
                array1D[index] = 0;
                printArray();
            } else {
                throw new IndexOutOfBoundsException("Posición Invalida");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearArray() {
        for (int i = 0; i < array1D.length; i++) {
            array1D[i] = 0;
        }
        printArray();
    }

    private void printArray() {
        String text = Arrays.toString(array1D);
        result.setText(text);
    }

    private void average() {
        double sum = 0;
        int noZero = 0;
        for (int i = 0; i < array1D.length; i++) {
            if (array1D[i] != 0) {
                noZero++;
            }
            sum += Math.abs(array1D[i]);
        }
        sum /= noZero;
        result.setText(String.valueOf(sum));
    }

    private void randomFill() {
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < array1D.length; i++) {
            array1D[i] = rnd.nextInt(100) + 1;
        }
        printArray();
    }

    private boolean validIndex() {
        int index = Integer.parseInt(position.getText());
        if (index >= 0 && index <= (array1D.length - 1)) {
            return true;
        }
        return false;
    }
}
