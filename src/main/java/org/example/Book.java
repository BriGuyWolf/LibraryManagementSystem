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


    /**
     * Categorizes the book based on page count using modern switch expression.
     */
    public String getCategory() {
        return switch (pages) {
            case 0 -> "Invalid";
            default -> {
                if (pages < 100) yield "Novella";
                else if (pages < 300) yield "Standard";
                else if (pages < 500) yield "Long";
                else yield "Epic";
            }
        };
    }

    /**
     * Gets reading difficulty based on page count and price.
     */
    public String getDifficulty() {
        return switch (getCategory()) {
            case "Novella" -> "Easy";
            case "Standard" -> "Moderate";
            case "Long" -> "Challenging";
            case "Epic" -> "Advanced";
            default -> "Unknown";
        };
    }

    /**
     * Gets a recommendation message based on book properties.
     */
    public String getRecommendation() {
        return switch (getCategory()) {
            case "Novella" -> String.format(
                    "Perfect for a quick read! Only %d pages.", pages
            );
            case "Standard" -> String.format(
                    "Great choice! A %d-page journey awaits.", pages
            );
            case "Long" -> String.format(
                    "Dive deep! %d pages of content to explore.", pages
            );
            case "Epic" -> String.format(
                    "An epic adventure! Prepare for %d pages of immersive reading.", pages
            );
            default -> "Book recommendation unavailable.";
        };
    }


}
