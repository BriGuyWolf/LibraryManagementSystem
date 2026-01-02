package org.example;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Library {
    private String name;
    private ArrayList<Readable> readableMaterial;

    public Library(String name) {
        this.name = name;
        readableMaterial = new ArrayList<>();
    }

    public String getName() { return name; }

    public ArrayList<Readable> getAllReadableMaterial() {
        return readableMaterial;
    }

    public void addReadableMaterial(Readable material) {
        readableMaterial.add(material);
        System.out.println("Added: " + material.getTitle());
    }

    public boolean removeReadableMaterial(String title) {
        for (var i = 0; i < readableMaterial.size(); i++) {
            if (readableMaterial.get(i).getTitle().equalsIgnoreCase(title.toUpperCase())) {
                readableMaterial.remove(i);
                System.out.println("Removed: " + title);
                return true;
            }
        }
        System.out.println("\"" + title + "\" was not found! Readable Material not removed!");
        return false;
    }

    public Readable findMaterialByTitle(String title) {
        for (Readable material : readableMaterial) {
            if (material.getTitle().equalsIgnoreCase(title)) {
                return material;
            }
        }
        return null;
    }


    public void printAllMaterials() {
        System.out.println("=========Library Catalog=========");
        if (readableMaterial.isEmpty()) {
            System.out.println("There are no materials within the library catalog.");
        } else {
            for (int i = 0; i < readableMaterial.size(); i++) {
                System.out.println((i+1) + ". " + readableMaterial.get(i));
            }
        }

        System.out.println("Total Books: " + readableMaterial.size());
    }

    public double getTotalValue() {
        double value = 0.00;
        for (Readable material : readableMaterial) {
            if (material instanceof Purchaseable p) {
                value += p.getPrice();
            }
        }
        return value;
    }

    public void getAllBooksOverPrice(double price) {
        System.out.println("=========Books over $" + price + "=========");
        int booksAbovePrice = 0;
        for (Readable material : readableMaterial) {
            if (material instanceof Purchaseable p) {
                if (p.getPrice() > price) {
                    booksAbovePrice++;
                    System.out.println(booksAbovePrice + ". " + p);
                }
            }
        }
        System.out.println("Total results: " + booksAbovePrice);
    }

    public void getBooksByAuthor(String author) {
        System.out.println("=========Author Search " + author + "=========");
        int booksByAuthor = 0;
        for (Readable material: readableMaterial) {
            if (material instanceof Book book) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    booksByAuthor++;
                    System.out.println(booksByAuthor + ". " + material);
                }
            }
        }
        System.out.println("Total results: " + booksByAuthor);
    }

    public Optional<Purchaseable> getMostExpensiveMaterial() {
        return getAllReadableMaterial().stream()
                .map(item -> (Purchaseable) item)
                .max(Comparator.comparingDouble(Purchaseable::getPrice));
    }

    public Optional<Purchaseable> getLeastExpensiveMaterial() {
        return getAllReadableMaterial().stream()
                .map(item -> (Purchaseable) item)
                .min(Comparator.comparingDouble(Purchaseable::getPrice));
    }


    /**
     * Gets detailed description of any readable item using pattern matching.
     */
    public String getItemDescription(Readable item) {
        if (item instanceof Book book) {
            return String.format(
                    "Book: '%s' by %s - %d pages, $%.2f (%s)",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPages(),
                    book.getPrice(),
                    book.getCategory()
            );
        } else if (item instanceof Magazine magazine) {
            return String.format(
                    "Magazine: %s - Issue #%d (%s)",
                    magazine.getTitle(),
                    magazine.getIssueNumber(),
                    magazine.getMonth()
            );
        } else {
            return "Unknown readable item";
        }
    }

    /**
     * Calculates discount based on item type using pattern matching.
     */
    public double calculateDiscount(Purchaseable item) {
        if (item instanceof Book book) {
            // Books: 10% discount if over $30
            return book.getPrice() > 30.0 ? book.getPrice() * 0.10 : 0.0;
        } else if (item instanceof Magazine magazine) {
            // Magazines: 5% discount always
            return magazine.getPrice() * 0.05;
        } else {
            return 0.0;
        }
    }

    /**
     * Determines if item qualifies for express shipping using pattern matching.
     */
    public boolean qualifiesForExpressShipping(Readable item) {
        if (item instanceof Book book) {
            // Books under 200 pages qualify
            return book.getPages() < 200;
        } else if (item instanceof Magazine magazine) {
            // Current month magazines qualify
            return magazine.getMonth().contains("2024") ||
                    magazine.getMonth().contains("2025");
        } else {
            return false;
        }
    }

}
