import java.util.*;
class Person {
    //instance variables
    private String firstName;
    private String lastName;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    //constructor
    Person(String firstName, String lastName, String phone, String street, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    //setters
    public void setPhone(String phone){
		this.phone = phone;
    }
    public void setStreet(String street){
		this.street = street;
    }
    public void setCity(String city){
		this.city = city;
    }
    public void setState(String state){
		this.state = state;
    }
    public void setZipCode(String zipCode){
		this.zipCode = zipCode;
    }

    //getters
    public String getPhone(){
	return phone;
	}
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getStreet(){
	return street;
	}
    public String getCity(){
	return city;
	}
    public String getState(){
	return state;
    }
    public String getZipCode(){
	return zipCode;
    }
	//print Single Entry
	public void printDetails(){
	    System.out.println("\t  ______________________________");
	    System.out.println();
	    System.out.println("\t  Name: \t" + this.getFirstName() + " " + this.getLastName());
            System.out.println("\t  Phone No: \t" + this.getPhone());
	    System.out.println("\t  Street: \t" + this.getStreet());
	    System.out.println("\t  City: \t" + this.getCity());
	    System.out.println("\t  State: \t" + this.getState());
	    System.out.println("\t  Zip Code: \t" + this.getZipCode());
	    System.out.println("\t  ______________________________");
	    System.out.println();
	}

	//Comparator to compare contacts by name (ascending order)
	public static Comparator<Person> nameComparator = new Comparator<Person>() {

	public int compare(Person p1, Person p2) {
	   String personLastName1 = p1.getLastName().toUpperCase();
	   String personLastName2 = p2.getLastName().toUpperCase();

	   if (personLastName1.equals(personLastName2))
	   {
	       String personFirstName1 = p1.getFirstName().toUpperCase();
	       String personFirstName2 = p2.getFirstName().toUpperCase();
	       return personFirstName1.compareTo(personFirstName2);
	   }
	   else
    	   return personLastName1.compareTo(personLastName2);

    }};

    //Comparator to compare contacts by zip code (ascending order)
    public static Comparator<Person> zipComparator = new Comparator<Person>() {

	public int compare(Person p1, Person p2) {
	   String personZip1 = p1.getZipCode();
	   String personZip2 = p2.getZipCode();

	   if (personZip1.equals(personZip2))
	   {
	       return nameComparator.compare(p1,p2);
	   }
	   else
    	   return personZip1.compareTo(personZip2);

    }};

}


public class AddressBook {
    //array list for all contacts in AddressBook
    private ArrayList<Person> contacts = new ArrayList<Person>();
    Scanner sc = new Scanner(System.in);

    //add contacts
    public void addContact() {
        System.out.println("Enter first name: ");
        String firstName = sc.next();
        System.out.println("Enter last name: ");
        String lastName = sc.next();
        System.out.println("Enter phone number: ");
        String phone = sc.next();
        System.out.println("Enter street address: ");
        String street = sc.next();
        System.out.println("Enter city: ");
        String city = sc.next();
        System.out.println("Enter state: ");
        String state = sc.next();
        System.out.println("Enter zipCode: ");
        String zipCode = sc.next();
        Person contact = new Person(firstName, lastName, phone, street, city, state, zipCode);
        contacts.add(contact);
        System.out.println("Contact successfully added to Address Book! \n");

    }

    // get index of contact to help search
    public int indexOfContact(String firstName, String lastName) {
        int index;
        for (index = 0; index < contacts.size(); index++) {
            if (contacts.get(index).getFirstName().equals(firstName) && contacts.get(index).getLastName().equals(lastName))
                return index;
        }
            return -1;
    }

    //update contact details based on user input
    public void updateContact() {
        System.out.println("Enter first name and last name of contact to be updated: ");
        String firstName = sc.next();
        String lastName = sc.next();
        int index = this.indexOfContact(firstName, lastName);
        if (index == -1)
            System.out.println("No Such Contact found!! \n");
        else
            {
                String continueUpdate="Yes";
                do {
                    System.out.println("What do you want to update?\n1. Phone\n2. Street\n3. City\n4. State\n5. Zip Code\n ");
    			    int choice = sc.nextInt();
        			switch(choice)
        			{
        				case 1:
        					System.out.println("Enter new phone number: ");
        					this.contacts.get(index).setPhone(sc.next());
        					System.out.println("Details Updated! \n");
        					break;
        				case 2:
        					System.out.println("Enter new street address: ");
        					this.contacts.get(index).setStreet(sc.next());
        					System.out.println("Details Updated! \n");
        					break;
        				case 3:
        					System.out.println("Enter new city: ");
        					this.contacts.get(index).setCity(sc.next());
        					System.out.println("Details Updated! \n");
        					break;
        				case 4:
        					System.out.println("Enter new state: ");
        					this.contacts.get(index).setState(sc.next());
        					System.out.println("Details Updated! \n");
        					break;
        				case 5:
        					System.out.println("Enter new zip code: ");
        					this.contacts.get(index).setZipCode(sc.next());
        					System.out.println("Details Updated! \n");
        					break;
        				default:
        					System.out.println("Invalid Input! \n");
        			}
        			System.out.println("Want to update something else? (Yes/No)");
        			continueUpdate=sc.next();
                }while(continueUpdate.equals("Yes"));
            }

    }

    //delete contact as specified by user
    public void removeContact() {
        System.out.println("Enter first name and last name of contact to be deleted: ");
        String firstName = sc.next();
        String lastName = sc.next();
        int index = this.indexOfContact(firstName, lastName);
        if (index == -1)
            System.out.println("No Such Contact found!! \n");
        else
        {
            System.out.println("Contact of " + this.contacts.get(index).getFirstName() + " deleted! \n");
            this.contacts.remove(index);
        }

    }

    public void sortByName() {
        Collections.sort(this.contacts, Person.nameComparator);
        System.out.println("Entries sorted by name successfully! \n");

    }

    public void sortByZip() {
        Collections.sort(this.contacts, Person.zipComparator);
        System.out.println("Entries sorted by zip code successfully! \n");
    }

    public void printContact() {
        System.out.println("Enter first name and last name of contact to be displayed: ");
        String firstName = sc.next();
        String lastName = sc.next();
        int index = this.indexOfContact(firstName, lastName);
        if (index == -1)
            System.out.println("No Such Contact found!! \n");
        else
        {
            this.contacts.get(index).printDetails();
        }

    }

    public void printAddressBook() {
        if (this.contacts.isEmpty())
            System.out.println("Address Book is empty!! \n");
        else {
            for (int index = 0; index < this.contacts.size(); index++) {
                this.contacts.get(index).printDetails();
            }
        }

    }

    public void displayMenu() {
        boolean exitMenu = false;
        while(!exitMenu)
		{
			System.out.println();
			System.out.println("\t-------------------------------------------------------------");
			System.out.println("\t                Address Book Management System");
			System.out.println("\t-------------------------------------------------------------");
			System.out.println("\t 1. Add Contact");
			System.out.println("\t 2. Edit/Update Contact");
			System.out.println("\t 3. Delete Contact");
			System.out.println("\t 4. Sort Address Book by Last Name ");
			System.out.println("\t 5. Sort Address Book by Zip Code ");
			System.out.println("\t 6. Display Contact");
			System.out.println("\t 7. Print Address Book");
			System.out.println("\t 8. Exit");
			System.out.println("\t-------------------------------------------------------------");
			System.out.println("\nEnter your choice: ");
            int choice = sc.nextInt();
			switch(choice)
			{
				case 1: addContact();
					break;
				case 2: updateContact();
					break;
				case 3: removeContact();
					break;
				case 4: sortByName();
					break;
				case 5: sortByZip();
					break;
				case 6: printContact();
					break;
				case 7: printAddressBook();
					break;
				case 8: exitMenu = true;
					break;
				default: System.out.println("Invalid Input! \n");
			}
		}
    }

	public static void main(String[] args) {
		AddressBook newAddressBook=new AddressBook();
		newAddressBook.displayMenu();
	}

}
