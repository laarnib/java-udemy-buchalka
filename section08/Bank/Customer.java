import java.util.ArrayList;

public class Customer {
    private String customerName;
    private ArrayList<Double> transactions;

    public Customer(String customerName, double initialTransactionAmount) {
        this.customerName = customerName;
        this.transactions = new ArrayList<>();
        transact(initialTransactionAmount);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void transact(double amount) {
        transactions.add(amount);
    }

    public void displayCustomerTransactions() {
        int i = 1;
        for (Double element : transactions) {
            System.out.println(i + ") Amount : " + element);
            i++;
        }
    }
}