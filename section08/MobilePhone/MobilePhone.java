import java.util.ArrayList;

public class MobilePhone {
    private String myFirstName;
    private String myLastName;
    private String myPhoneNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone() {
        this("", "", "");
        this.myContacts = new ArrayList<>();
    }

    public MobilePhone(String myPhoneNumber) {
        this("", "", myPhoneNumber);
        this.myContacts = new ArrayList<>();
    }

    public MobilePhone(String myFirstName, String myLastName, String myPhoneNumber) {
        this.myFirstName = myFirstName;
        this.myLastName = myLastName;
        this.myPhoneNumber = myPhoneNumber;
        this.myContacts = new ArrayList<>();

    }

    public String getMyFirstName() {
        return myFirstName;
    }

    public String getMyLastName() {
        return myLastName;
    }

    public String getMyPhoneNumber() {
        return myPhoneNumber;
    }

    public String getMyFullName() {
        return myFirstName + " " + myLastName;
    }

    /* Prints contact list. If contact list is empty,
    prints "Your contact list is empty." */
    public void printContactList() {
        System.out.println("");
        if (myContacts.size() == 0) {
            System.out.println("Your contact list is empty.");
        } else {
            for (Contact contact : myContacts) {
                System.out.println((myContacts.indexOf(contact) + 1) + ". " + contact);
            }
        }
    }

    /* Adds new contact if contact doesn't already exists.
    Returns true if contact is successfully added,
    false otherwise */
    public boolean addNewContact(Contact newContact) {
        int index = findContactFromList(newContact);

        if (index >= 0) {
            return false;
        }

        myContacts.add(newContact);
        return true;
    }

    /* Updates existing contact. If contact to be updated does not exist,
    action is not performed. Otherwise, new information is checked for
    duplication. If new information is not in the list, contact is updated
    with the new information. */
    public void updateContact(Contact currentContact, Contact newContact) {
        boolean isDuplicateInformation = isDuplicateContactInformation(newContact);

        if (isDuplicateInformation) {
            System.out.println("Similar contact already exists. Update not executed.");
        } else {
            int index = myContacts.indexOf(currentContact);
            myContacts.set(index, newContact);
            System.out.println("Update successful.");
            System.out.println("Here is the updated contact information: " + myContacts.get(index));
        }
    }

    /* Search contact list. If contact is found, return contact.
    Otherwise, display message that contact was not found in the list. */
    public Contact searchContactFromList(String name) {
        int index = findContactFromList(name);
        if (index >= 0) {
            System.out.println("\n" + name + " found.");
            return myContacts.get(index);
        }

        return null;
    }

    // Searches for contact in the list
    private int findContactFromList(Contact contact) {
        return findContactFromList(contact.getFullName());
    }

    // Searches for contact in the list
    private int findContactFromList(String name) {
        for (Contact contact : myContacts) {
            if (contact.getFullName().toLowerCase().equals(name.toLowerCase())) {
                return myContacts.indexOf(contact);
            }
        }
        return -1;
    }

    // Check for duplicate information of contact
    private boolean isDuplicateContactInformation(Contact contactToBeCompared) {
        for (Contact contact : myContacts) {
            if (contact.getFullName().toLowerCase().equals(contactToBeCompared.getFullName().toLowerCase()) &&
                    contact.getPhoneNumber().equals(contactToBeCompared.getPhoneNumber())) {
                return true;
            }
        }

        return false;
    }

    // Remove contact from list. Return true if operation is successful, false otherwise.
    public boolean removeContact(String name) {
        Contact searchedContact = searchContactFromList(name);
        if (searchedContact != null) {
            System.out.println("Removing " + searchedContact + " from your list...");
            myContacts.remove(searchedContact);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return myFirstName + " " + myLastName + ": " + myPhoneNumber;
    }
}