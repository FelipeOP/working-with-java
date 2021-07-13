package JFrame.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import JFrame.Class.Client;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GUIMethods extends JFrame implements ActionListener {

    private JPanel topPanel, botPanel;
    private JLabel labels[] = new JLabel[4];
    private JTextField fields[] = new JTextField[3];
    private JButton button;
    private JTable table;
    private DefaultTableModel dtm;
    private String columnNames[] = { "# cuenta", "Propietario", "Saldo" };
    private Vector<Client> clients = new Vector<Client>();
    private Object[] obj = new Object[columnNames.length];
    private JScrollPane scp;

    public static void main(String[] args) {
        new GUIMethods();
    }

    public GUIMethods() {
        createLabels();
        createFields();
        createPanels();
        createTable();
        addComponents();
        deployFrame();
    }

    private void deployFrame() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setTitle("Accounts");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(360, 550));
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createTable() {
        table = new JTable();
        table.setModel(new DefaultTableModel(null, columnNames));
        // Not editable
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        scp = new JScrollPane(table);
        scp.setPreferredSize(new Dimension(300, 170));
        dtm = (DefaultTableModel) table.getModel();
    }

    private void createPanels() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setPreferredSize(new Dimension(300, 270));
        topPanel.setBackground(Color.WHITE);
        botPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botPanel.setPreferredSize(new Dimension(300, 200));
        botPanel.setBackground(Color.WHITE);
    }

    private void createLabels() {
        for (int l = 0; l < labels.length; l++) {
            labels[l] = new JLabel();
            // Simulate that Labels aren't affected by the FlowLayout
            labels[l].setHorizontalAlignment(SwingConstants.LEFT);
            labels[l].setPreferredSize(new Dimension(280, 10));
        }
        labels[0].setText("Número de cuenta");
        labels[1].setText("Nombre del cliente");
        labels[2].setText("Saldo de la cuenta");
        labels[3].setText("Resultado");
        labels[3].setPreferredSize(new Dimension(300, 10));
        labels[3].setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void createFields() {
        button = new JButton("Desplegar cuenta");
        button.addActionListener(this);
        button.setFocusable(false);
        for (int f = 0; f < fields.length; f++) {
            fields[f] = new JTextField(25);
        }
    }

    private void addComponents() {
        for (int c = 0; c < fields.length; c++) {
            topPanel.add(labels[c]);
            topPanel.add(fields[c]);
        }
        topPanel.add(button);
        this.add(topPanel);
        botPanel.add(labels[3]);
        botPanel.add(scp);
        this.add(botPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button)) {
            verifyFields();
            createAccount();
        }
    }

    private void createAccount() {
        try {
            Client client = getClientData();
            if (isRegistered(client)) {
                throw new Exception("Cliente ya registrado");
            } else {
                clients.add(client);
                obj[0] = clients.lastElement().getAccountNumber();
                obj[1] = clients.lastElement().getName();
                obj[2] = clients.lastElement().getBalance();
                dtm.addRow(obj);
                clearAll();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void clearAll() {
        for (JTextField tf : fields) {
            tf.setText("");
        }
    }

    private boolean isRegistered(Client client) {
        for (Client c : clients) {
            if (c.getAccountNumber() == client.getAccountNumber()) {
                return true;
            }
        }
        return false;
    }

    private Client getClientData() {
        Client client = new Client();
        client.setAccountNumber(Integer.parseInt(fields[0].getText()));
        client.setName(fields[1].getText());
        client.setBalance(Double.parseDouble(fields[2].getText()));
        return client;
    }

    private void verifyFields() {
        for (int t = 0; t < fields.length; t++) {
            if (fields[t].getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, labels[t].getText() + " vacio", "Campo vacio",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}