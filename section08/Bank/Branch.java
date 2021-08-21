import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customers;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.customers = new ArrayList<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomerList() {
        return customers;
    }

    public ArrayList<Customer> printBranchCustomerList(String branchName) {
        return customers;
    }

    public boolean addNewCustomer(String newCustomerName, double initialTransactionAmount) {
        int index = queryCustomer(newCustomerName);

        if (index >= 0) {
            System.out.println(newCustomerName + " is already a current client and was not added to the branch.");
            return false;
        }

        customers.add(new Customer(newCustomerName, initialTransactionAmount));
        return true;
    }

    public int queryCustomer(String customerName) {
        for (Customer customer : customers) {
            if (customer.getCustomerName().toLowerCase().equals(customerName.toLowerCase()))
                return customers.indexOf(customer);

        }
        return -1;
    }

    public void addTransaction(double amount, int position) {
        customers.get(position).transact(amount);
    }

    public void printCustomerList() {
        for (Customer customer : customers) {
            System.out.println("\n" + customer.getCustomerName() + "'s Transactions:");
            customer.displayCustomerTransactions();
        }
    }

    @Override
    public String toString() {
        return this.branchName;
    }
}
