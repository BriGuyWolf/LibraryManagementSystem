package org.example;

public class Book implements Readable, Purchaseable {
    private String title;
    private String author;
    private int pages;
    private double price;

    // Constructor
    public Book(String title, String author, int pages, double price) {
        this.title = title;      // 'this' refers to the current object
        this.author = author;
        this.pages = pages;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }

    }

    public String getDescription() {
        return "'" + title + "' by " + author + " (" + pages + " pages)";
    }

    @Override
    public String toString() {
        return getDescription() + " - $" + price;
    }

    @Override
    public String getContent() {
        return "Book Content: " + getDescription();
    }

    @Override
    public int getReadingTimeMinutes() {
        return (pages * 250) / 200;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }


}
