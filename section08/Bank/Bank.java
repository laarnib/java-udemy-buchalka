import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Branch> branches;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.branches = new ArrayList<>();
    }

    public String getBankName() {
        return bankName;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public boolean addBranch(String newBranchName) {
        int branchIndex = findBranch(newBranchName);

        if (branchIndex >= 0) {
            System.out.println("\n" + newBranchName + " is not added to the list because it already exists.");
            return false;
        }

        branches.add(new Branch(newBranchName));
        return true;
    }

    public boolean addCustomerToBranch(String branchName, String customerName, double initialTransactionAmount) {
        int branchIndex = findBranch(branchName);
        boolean isSuccessful = false;

        if (branchIndex >= 0) {
            if (branches.get(branchIndex).addNewCustomer(customerName, initialTransactionAmount)) {
                System.out.println("\nCustomer was successfully added to " + branchName + ".");
                isSuccessful = true;
            } else {
                System.out.println("Error adding customer to the branch.");
            }
        } else {
            System.out.println("\nBranch not found. Customer was not added.");
        }

        return isSuccessful;
    }

    public void addACustomerTransaction(String branchName, String customerName, double amount) {
        int index = findBranch(branchName);

        if (index >= 0) {
            int customerIndex = branches.get(index).queryCustomer(customerName);

            if (customerIndex >= 0) {
                branches.get(index).addTransaction(amount, customerIndex);
                System.out.println("Transaction successful.");
            } else {
                System.out.println("Customer not found. Transaction not added.");
            }
        } else {
            System.out.println("Branch not found.");
        }
    }

    private int findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getBranchName().toLowerCase().equals(branchName.toLowerCase()))
                return branches.indexOf(branch);
        }

        return -1;
    }

    public void getBranchCustomerList(String branchName) {
        int index = findBranch(branchName);

        if (index >= 0) {
            branches.get(index).printCustomerList();
        } else {
            System.out.println("Branch does not exist.");
        }
    }
}
