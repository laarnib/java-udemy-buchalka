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

    public boolean addNewContact(Contact newContact) {
        int index = findContactFromList(newContact);

        // Check if new contact to be added already exists
        if (index >= 0) {
            return false;
        }

        myContacts.add(newContact);
        return true;
    }

    private int findContactFromList(Contact contact) {
        return findContactFromList(contact.getFullName());
    }

    private int findContactFromList(String name) {
        for (Contact contact : myContacts) {
            if (contact.getFullName().equals(name)) {
                return myContacts.indexOf(contact);
            }
        }
        return -1;
    }

    public Contact searchContactFromList(String name) {
        int index = findContactFromList(name);
        if (index >= 0) {
            System.out.println(name + " found.");
            return myContacts.get(index);
        }

        return null;
    }

    public int updateContact(Contact currentContact, Contact newContact) {
        int index = findContactFromList(currentContact);

        if (index >= 0) {
            // Check if the new information already exists
            if (findContactFromList(newContact) >= 0) {
                System.out.println("Contact already exists. Update not executed.");
                return -1;
            }

            myContacts.set(index, newContact);
        }

        return index;
    }

    public boolean removeContact(String name) {
        Contact searchedContact = searchContactFromList(name);
        if (searchedContact != null) {
            System.out.println("Removing " + searchedContact + " from your list...");
            myContacts.remove(searchedContact);
            return true;
        }

        return false;
    }

    // Prints contact list
    public void printContactList() {
        System.out.println("");
        if (myContacts.size() == 0) {
            System.out.println("Your contacts is empty.");
        } else {
            for (Contact contact : myContacts) {
                System.out.println((myContacts.indexOf(contact) + 1) + ". " + contact);
            }
        }
    }

    public void printAContact(int index) {
        System.out.println(myContacts.get(index));
    }

    @Override
    public String toString() {
        return myFirstName + " " + myLastName + ": " + myPhoneNumber;
    }
}