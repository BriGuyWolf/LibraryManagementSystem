package org.example;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class LibraryStreams {
    public static void main(String[] args) {
        // Create a collection of books
        List<Book> books = Arrays.asList(
                new Book("1984", "George Orwell", 328, 15.99),
                new Book("Animal Farm", "George Orwell", 112, 12.99),
                new Book("The Hobbit", "J.R.R. Tolkien", 310, 14.99),
                new Book("The Lord of the Rings", "J.R.R. Tolkien", 1178, 29.99),
                new Book("Clean Code", "Robert Martin", 464, 39.99),
                new Book("The Pragmatic Programmer", "David Thomas", 352, 42.99),
                new Book("Dune", "Frank Herbert", 688, 18.99)
        );

        Consumer<String> printHeader = s -> System.out.println("\n=== " + s + " ===\n");

        printHeader.accept("All books - forEach");
        books.forEach(System.out::println);

        printHeader.accept("George Orwell Books");

        List<Book> orwellBooks = books.stream()
                .filter(n -> n.getAuthor().equalsIgnoreCase("George Orwell"))
                .toList();
        orwellBooks.forEach(System.out::println);

        printHeader.accept("Books under $20");
        List<Book> booksUnder20Dollars = books.stream()
                .filter(n -> n.getPrice() < 20)
                .toList();
        booksUnder20Dollars.forEach(System.out::println);

        printHeader.accept("Map All Titles");
        List<String> titles = books.stream()
                .map(Book::getTitle)
                .toList();
        System.out.println(titles);

        printHeader.accept("Cheapest book using Optional");
        Optional<Book> cheapest = books.stream()
                .min(Comparator.comparingDouble(Book::getPrice));

        cheapest.ifPresentOrElse(
                book -> System.out.println("Cheapest Book: " + book),
                () -> System.out.println("No books were found")
        );

        // ===== COMPLEX QUERY =====
        printHeader.accept("=== Complex Query: Affordable Long Books ===");
        System.out.println("(Over 300 pages, under $30, sorted by value)");

        books.stream()
                .filter(book -> book.getPages() > 300 && book.getPrice() < 30)
                .sorted(Comparator.comparingDouble(book -> book.getPrice() / book.getPages()))
                .forEach(book -> {
                    double valuePerPage = book.getPrice() / book.getPages();
                    System.out.printf("%s - %d pages, $%.2f ($%.4f per page)%n",
                            book.getTitle(), book.getPages(), book.getPrice(), valuePerPage);
                });

        // Failed Optiopnal
        // Find book by title
        Optional<Book> found = books.stream()
                .filter(book -> book.getTitle().equals("Dunes"))
                .findFirst();

        found.ifPresentOrElse(
                book -> System.out.println("Found Dune! " + book.getPages() + " pages"),
                () -> System.out.println("Not found")
        );

        printHeader.accept("Average Price");
        double average = books.stream()
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("Average Price: $" + average);
    }
}
