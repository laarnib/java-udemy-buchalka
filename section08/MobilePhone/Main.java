import java.util.Scanner;

/* Constraints:
1. Full name of contact must be provided when performing search, update, and remove.
2. Cannot search contact based on phone number.
 */

public class MobilePhoneTestDrive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ui.start(); // starts the MobilePhone application
    }
}