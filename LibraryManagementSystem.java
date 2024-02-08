
import java.util.*;

//interface specifying operations that can be performed in the library
interface LibrarySErviceInterface {
    void addItem(Item item);

    void registerMember(LibraryMember member);

    void checkOutItem(int itemID, int memberID);

    void returnItem(int itemID, int memberID);

    void checkStatus(int itemID); // Displaying the details of an item and the current status (checked out/in).

    void diplayItems();
}

// class representing library items
abstract class Item {
    private int id;
    private String title;
    private String Author;

    // constructor for setting values
    public Item(int id, String title, String Author) {
        this.id = id;
        this.title = title;
        this.Author = Author;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return Author;
    }

    // abstract method which is implemented in inherited classes i.e book and
    // magazine
    public abstract void displayDetails();
}

// Book class inheriting from item
class Book extends Item {
    private int isbn;

    // constructor
    public Book(int id, String title, String author, int isbn) {
        super(id, title, author);
        this.isbn = isbn;
    }

    public int getIsbn() {
        return isbn;
    }

    public void displayDetails() {
        System.out.println("BOOK:");
        System.out.print(" ID: " + getId());
        System.out.print(" Title: " + getTitle());
        System.out.print(" Author: " + getAuthor());
        System.out.print(" ISBN: " + getIsbn());
        System.out.println();
    }
}

// Magazine class inherited from Item
class Magazine extends Item {
    private int issueNumber;

    // constructor
    public Magazine(int id, String title, String author, int isbn) {
        super(id, title, author);
        this.issueNumber = isbn;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void displayDetails() {
        System.out.println("MAGAZINE:");
        System.out.print(" ID: " + getId());
        System.out.print(", Title: " + getTitle());
        System.out.print(", Author: " + getAuthor());
        System.out.print(", Issue Number: " + getIssueNumber());
        System.out.println();
    }
}

class LibraryMember {
    private int memberID;
    private String name;

    // constructor
    public LibraryMember(int memberID, String name) {
        this.memberID = memberID;
        this.name = name;
    }

    // getters
    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }
}

class Library implements LibrarySErviceInterface {
    private Map<Integer, Item> inventory;
    private Map<Integer, LibraryMember> members;

    // constructor
    public Library() {
        inventory = new HashMap<>();
        members = new HashMap<>();
    }

    // function to add items
    public void addItem(Item item) {
        inventory.put(item.getId(), item);
        System.out.println("Item added succsesfully!");
    }

    public void registerMember(LibraryMember member) {
        members.put(member.getMemberID(), member);
        System.out.println("Member registered succsesfully!");
    }

    // function to check out item
    public void checkOutItem(int itemID, int memberID) {
        Item item = inventory.get(itemID);
        LibraryMember member = members.get(memberID);
        if (item != null && member != null) {
            inventory.remove(itemID);
            System.out.println("Item issued to member successfully!");
        } else {
            System.out.println("Item or member not found!");
        }

    }

    // function to return item
    public void returnItem(int itemID, int memberID) {
        Item item = inventory.get(itemID);
        LibraryMember member = members.get(memberID);
        if (item != null && member != null) {
            inventory.put(itemID, item);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Item or member not found!");
        }
    }

    // function to check status of the item
    public void checkStatus(int itemID) {
        if (inventory.containsKey(itemID)) {
            System.out.println("Item is present in the inventory!");
        } else {
            System.out.println("Item is not present in the inventory!");
        }
    }

    // fucntion to display all items
    public void diplayItems() {
        if (inventory.isEmpty()) {
            System.out.println("Nothing to be displayed!");
        } else {
            for (int mp : inventory.keySet()) {
                inventory.get(mp).displayDetails();
            }
        }
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        boolean flag = true;

        do {
            System.out.println("****************Welcome to the Library Management System*****************");
            System.out.println("1. Add Item\n" +
                    "2. Register Member\n" +
                    "3. Check Out Item\n" +
                    "4. Return Item\n" +
                    "5. Check Status of Item\n" +
                    "6. Display all items present in inventory\n" +
                    "7. Exit");

            System.out.print("Enter your choice ");
            int n = sc1.nextInt();

            switch (n) {
                case 1:
                    System.out.print("If you want to add Book enter B if you wnat to add Magazine enter M: ");
                    char ch = sc1.next().charAt(0);
                    if (ch == 'B') {
                        System.out.print("Eneter the Item number: ");
                        int id = sc1.nextInt();
                        System.out.print("Enter the title:");
                        String title = sc2.nextLine();
                        System.out.print("Enter the author: ");
                        String author = sc2.nextLine();
                        System.out.print("Enter the isbn: ");
                        int isbn = sc1.nextInt();
                        Book b1 = new Book(id, title, author, isbn);
                        library.addItem(b1);
                    } else if (ch == 'M') {
                        System.out.print("Enter the Item number: ");
                        int id = sc1.nextInt();
                        System.out.print("Enter the title: ");
                        String title = sc2.nextLine();
                        System.out.print("Enter the author: ");
                        String author = sc2.nextLine();
                        System.out.print("Issue Number: ");
                        int issueNumber = sc1.nextInt();
                        Magazine m1 = new Magazine(id, title, author, issueNumber);
                        library.addItem(m1);
                    } else {
                        System.out.println("Invalid Input!");
                    }
                    break;

                case 2:
                    System.out.println("Enter the name of the member: ");
                    String name = sc2.nextLine();
                    System.out.println("Enter the memeber ID:");
                    int memberID = sc1.nextInt();
                    LibraryMember mem = new LibraryMember(memberID, name);
                    library.registerMember(mem);
                    System.out.println("Member Added Successfully!!");
                    break;

                case 3:
                    System.out.println("Enter the itemID ot be issued: ");
                    int itemID = sc1.nextInt();
                    System.out.println("Enter the memberId to whom the item should be issued: ");
                    int memID = sc1.nextInt();
                    library.checkOutItem(itemID, memID);
                    break;

                case 4:
                    System.out.println("Enter the itemID ot be returned: ");
                    int itID = sc1.nextInt();
                    System.out.println("Enter the memberId who is returning the item: ");
                    int mID = sc1.nextInt();
                    library.returnItem(itID, mID);
                    break;

                case 5:
                    System.out.println("Enter the itemID whose status is to be checked: ");
                    int itid = sc1.nextInt();
                    library.checkStatus(itid);
                    break;

                case 6:
                    library.diplayItems();

                case 7:
                    flag = false;

                default:
                    System.out.println("Please Enter the valid input!!");
                    flag = false;
                    break;
            }

        } while (flag);
        sc1.close();
        sc2.close();
    }

}
