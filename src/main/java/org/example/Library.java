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
}
