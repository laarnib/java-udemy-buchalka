import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Bank bank;

    public UserInterface(Scanner scanner, Bank bank) {
        this.scanner = scanner;
        this.bank = bank;
    }

    // Starts the application
    public void startBankApplication() {
        int option = -1;

        System.out.println("\n" + bank.getBankName() + "\n");
        while(option != 0) {
            displayMainMenu();
            System.out.print("\nChoose an option by entering the number that corresponds to your selected operation? ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch(option) {
                    case 1 :
                        addANewBranch();
                        break;
                    case 2 :
                        addCustomer();
                        break;
                    case 3 :
                        addCustomerTransaction();
                        break;
                    case 4 :
                        printBranchCustomerList();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option.");
            }
        }

        System.out.println("Shutting down the application...");
    }

    // Display main menu
    private void displayMainMenu() {
        System.out.println("\nBanking Application Options:");
        System.out.println("0 - Exit Banking Application");
        System.out.println("1 - Add a branch");
        System.out.println("2 - Add a customer to a branch");
        System.out.println("3 - Add a transaction for an existing customer");
        System.out.println("4 - Print list of customers with their transactions of a branch ");
    }

    // Add a new branch
    private void addANewBranch() {
        while (true) {
            System.out.print("Branch Name: ");
            String branchName = scanner.nextLine();
            if (!branchName.isEmpty()) {
                System.out.println("\nAdding " + branchName + " to " + bank.getBankName());
                if (bank.addBranch(branchName)) {
                    System.out.println(branchName + " was successfully added to " + bank.getBankName());
                } else {
                    System.out.println("Duplicate branch. " + branchName + " was not added to " + bank.getBankName());
                }

                break;
            }
        }
    }

    // Add a customer
    private void addCustomer() {
        String branchName = "";
        String customerName = "";
        double initialTransactionAmount = 0.0;

        System.out.println("\nLet's add a customer. Please provide the following information: ");

        System.out.print("Branch name: ");
        branchName = scanner.nextLine();
        System.out.print("Customer name: ");
        customerName = scanner.nextLine();

        initialTransactionAmount = getTransactionAmount();
        bank.addCustomerToBranch(branchName, customerName, initialTransactionAmount);
    }

    // Add a customer transaction
    private void addCustomerTransaction() {
        System.out.println("Branch name: ");
        String branchName = scanner.nextLine();
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();
        double transactionAmount = getTransactionAmount();

        bank.addACustomerTransaction(branchName, customerName, transactionAmount);
    }

    // Print a branch's customer list
    private void printBranchCustomerList() {
        System.out.println("Branch name: ");
        String branchName = scanner.nextLine();

        bank.getBranchCustomerList(branchName);
    }

    /* Get transaction amount. If amount is a non numeric value or less than zero,
     amount is set to zero and an error message is displayed.*/
    private double getTransactionAmount() {
        double amount = 0;

        System.out.print("Amount: ");
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.out.println("The amount you entered is less than zero. Your transaction amount will be changed to zero.");
                amount = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Amount.");
        }

        return amount;
    }
}