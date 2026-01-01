package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Main {
    public static void main(String[] args) {


//        // Create Book objects
//        Book book1 = new Book("1984", "George Orwell", 328, 15.99);
//        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 310, 12.99);
//        Book book3 = new Book("Clean Code", "Robert Martin", 464, 39.99);
//
//        // Print book details using toString()
//        System.out.println("Books in our collection:");
//        System.out.println(book1);
//        System.out.println(book2);
//        System.out.println(book3);
//        System.out.println();
//
//        // Use getter methods
//        System.out.println("First book title: " + book1.getTitle());
//        System.out.println("First book author: " + book1.getAuthor());
//        System.out.println();
//
//        // Use setter to change price
//        System.out.println("Changing price of Clean Code...");
//        book3.setPrice(29.99);
//        System.out.println(book3);
//        System.out.println();
//
//        // Use custom method
//        System.out.println("Description: " + book2.getDescription());
//
//        // Test inheritance
//        System.out.println("\n=== Testing Inheritance ===");
//
//        EBook ebook1 = new EBook(
//                "Effective Java",
//                "Joshua Bloch",
//                416,
//                45.99,
//                5.2,
//                "PDF"
//        );
//
//        EBook ebook2 = new EBook(
//                "Java: The Complete Reference",
//                "Herbert Schildt",
//                1248,
//                55.00,
//                12.8,
//                "EPUB"
//        );
//
//        // EBook has all Book methods
//        System.out.println(ebook1.getTitle());  // Inherited from Book
//        System.out.println(ebook1.getAuthor()); // Inherited from Book
//
//        // EBook has its own methods
//        System.out.println(ebook1.getDownloadInfo());
//
//        // Overridden method
//        System.out.println(ebook1.getDescription());  // EBook's version
//        System.out.println();
//        System.out.println(ebook2);
//
//        // Test interface
//        System.out.println("\n=== Testing Interface ===");
//
//        // Book is Readable
//        Readable readable1 = book1;  // Polymorphism!
//        System.out.println("Reading time: " + readable1.getReadingTimeMinutes() + " minutes");
//        System.out.println("Available: " + readable1.isAvailable());
//
//        // EBook is also Readable (inherits from Book)
//        Readable readable2 = ebook1;
//        System.out.println("Reading time: " + readable2.getReadingTimeMinutes() + " minutes");

        // Create a library
        Library myLibrary = new Library("Brian's Library");

        // Add books
        myLibrary.addReadableMaterial(new Book("1984", "George Orwell", 328, 15.99));
        myLibrary.addReadableMaterial(new Book("The Hobbit", "J.R.R. Tolkien", 310, 12.99));
        myLibrary.addReadableMaterial(new EBook("Effective Java", "Joshua Bloch", 416, 45.99, 5.2, "PDF"));
        myLibrary.addReadableMaterial(new Magazine("PC Magazine", 172, 6.99, 124, "December 2025"));

        // Print all books
        myLibrary.printAllMaterials();

        // Find a book
        System.out.println("\n=== Searching for '1984' ===");
        Readable found = myLibrary.findMaterialByTitle("1984");
        if (found != null) {
            System.out.println("Found: " + found);
            System.out.println("Reading time: " + found.getReadingTimeMinutes() + " minutes");
        }

        // Calculate total value
        System.out.println("\n=== Library Statistics ===");
        System.out.println("Total value: $" + myLibrary.getTotalValue());

        // Remove a book
        System.out.println("\n=== Removing a book ===");
        myLibrary.removeReadableMaterial("The Hobbit");
        myLibrary.printAllMaterials();

    }
}
