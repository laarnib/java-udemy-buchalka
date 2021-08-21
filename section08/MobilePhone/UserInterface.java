import java.util.Scanner;

public class UserInterface {
    private static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    private MobilePhone myMobilePhone;
    private final Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.myMobilePhone = new MobilePhone();
        this.scanner = scanner;
    }

    // Starts the mobile phone application
    public void start(){
        createMobilePhone();

        int option = -1;
        while(option != 0) {
            clearConsole(1000);
            displayWelcomeScreen();
            displayPhoneOptions();
            System.out.print("\nWhich option do you want to do? ");
            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 0 :
                        System.out.println(" \nTurning off your phone in 3.. 2.. 1..");
                        System.out.println(" \nShutting down...");
                        break;
                    case 1 :
                        System.out.println(" \nRetrieving your contact list...");
                        displayContactList();
                        break;
                    case 2 :
                        System.out.println(" \nAdding a new contact...");
                        addContact();
                        break;
                    case 3 :
                        System.out.println(" \nUpdating a contact...");
                        updateContactInformation();
                        break;
                    case 4 :
                        System.out.println(" \nLet's find a contact...");
                        searchForAContact();
                        break;
                    case 5 :
                        System.out.println(" \nLet's remove a contact from your list...");
                        removeContactFromList();
                        break;
                    default:
                        System.out.println("Ooops! Option not found.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
            }
        }

        scanner.close();
        clearConsole(1000);
    }

    /* Add new contact. If contact to be added already exists,
    operation will not be executed. */
    private void addContact() {
        clearConsole(1000);
        System.out.println("\nLet's add a new contact.");
        System.out.print("\nFirst Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // Create a new contact
        Contact newContact = new Contact(firstName, lastName, phoneNumber);
        if (myMobilePhone.addNewContact(newContact)) {
            System.out.println(" \n" + newContact + " was added to your list.");
        } else {
            System.out.println(" \n" + newContact.getFullName() + " already exists and was not added to your list.");
        }

        returnToMainMenu();
    }

    // Display contact list
    private void displayContactList() {
        clearConsole(1000);
        myMobilePhone.printContactList();
        returnToMainMenu();
    }

    /* Remove existing contact from the list, otherwise
    operation is not performed and a message prompt is displayed. */
    private void removeContactFromList() {
        System.out.print("Who do you want to remove? Provide the fullname: ");
        String name = scanner.nextLine();
        if (myMobilePhone.removeContact(name)) {
            System.out.println("\n" + name + " is no longer in your contact list.");
        } else {
            System.out.println("\n" + name + " not found. No contact was deleted.");
        }

        returnToMainMenu();
    }

    /* Search existing contact. If found, display searched contact's information.
    Otherwise, display message that the searched contact is not in the list. */
    private void searchForAContact() {
        System.out.print("Searching for? Please provide the fullName: ");
        String name = scanner.nextLine();

        Contact foundContact = myMobilePhone.searchContactFromList(name);
        if (foundContact != null) {
            System.out.println("Here is " + name + "'s information : " + "\n" + foundContact);
        } else {
            System.out.println(name + " is not on your list.");
        }

        returnToMainMenu();
    }

    /* Update existing contact information.
    If contact to be updated is not in the list, update will not be executed. */
    private void updateContactInformation() {
        clearConsole(500);
        myMobilePhone.printContactList();
        System.out.print("\nWhich contact do you want to update? Provide the full name : ");
        String name = scanner.nextLine();

        // Check if contact to be updated exists in the list
        Contact foundContact = myMobilePhone.searchContactFromList(name);

        if (foundContact != null) {
            Contact upDatedContact = getNewContactInformation(foundContact);
            System.out.println("\nUpdating Information...");
            myMobilePhone.updateContact(foundContact, upDatedContact);
        } else {
            System.out.println("Contact not found. Cannot perform update.");
        }

        returnToMainMenu();
    }

    // Get new information of the contact that needs to be updated.
    private Contact getNewContactInformation(Contact contact) {
        int option = -1;
        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String phoneNumber = contact.getPhoneNumber();

        System.out.println("Let's update " + contact.getFullName() + "'s information.");
        while (option != 0) {
            displayInformationOptions();
            System.out.print("\nType the number of the information you want to update: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
                switch(option) {
                    case 0 :
                        System.out.println("Going back to the main menu");
                        break;
                    case 1 :
                        System.out.print("First name: ");
                        firstName = scanner.nextLine();
                        break;
                    case 2 :
                        System.out.print("Last name: ");
                        lastName = scanner.nextLine();
                        break;
                    case 3 :
                        System.out.print("Phone number: ");
                        phoneNumber = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid.");
            }
        }

        return (new Contact(firstName, lastName, phoneNumber));
    }

    // Display options for updating contact information
    private void displayInformationOptions() {
        System.out.println("\nWhich information do you want to update? ");
        System.out.println("0 - back to main menu");
        System.out.println("1 - to update first name");
        System.out.println("2 - to update last name");
        System.out.println("3 - to update phone number");
    }

    // Clear the console screen
    private void clearConsole(int time) {
        try {
            Thread.sleep(time);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Display Welcome Screen message
    private void displayWelcomeScreen() {
        System.out.println(TEXT_PURPLE + "\n ***** Welcome to " + myMobilePhone.getMyFirstName() + "'s " + "Phone *****" + TEXT_RESET);
    }

    // Create MobilePhone object
    private void createMobilePhone() {
        System.out.println("Hello");
        System.out.println("Let's get you set up");
        myMobilePhone = getMobilePhoneOwnerInformation();
    }

   // Get the mobile phone's owner's information and returns the created MobilePhone object
   private MobilePhone getMobilePhoneOwnerInformation() {
       System.out.print("Provide your first name: ");
       String firstName = scanner.nextLine();
       System.out.print("Provide your last name: ");
       String lastName = scanner.nextLine();
       System.out.print("What's the number for this phone? ");
       String phoneNumber = scanner.nextLine();

       return (new MobilePhone(firstName, lastName, phoneNumber));
   }

   // Display the phone's options
   private void displayPhoneOptions() {
       System.out.println("\nChoose from the following options by typing the number of your desired action: ");
       System.out.println("\t 0 - to turn off the phone");
       System.out.println("\t 1 - to list your existing contacts");
       System.out.println("\t 2 - to add a new contact");
       System.out.println("\t 3 - to update your existing contact");
       System.out.println("\t 4 - to search a contact from your list");
       System.out.println("\t 5 - to remove a contact from your list");
   }

   // Return to main menu options
   private void returnToMainMenu() {
       System.out.print(" \n\nPress enter or the return key to go back to the main menu.");
       scanner.nextLine();
   }

}