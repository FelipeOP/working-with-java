package JFrame.Class;

public class Client {
    private int accountNumber;
    private String name;
    private Double balance;

    public Client() {
    }

    public Client(Client client) {
        this.accountNumber = client.getAccountNumber();
        this.name = client.getName();
        this.balance = client.getBalance();
    }

    public Client(int accountNumber, String name, Double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
