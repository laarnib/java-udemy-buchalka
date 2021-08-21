import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank secu = new Bank("Security Bank");
        UserInterface ui = new UserInterface(scanner, secu);

        ui.startBankApplication();
    }
}
