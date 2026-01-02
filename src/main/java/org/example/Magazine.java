package org.example;

public class Magazine implements Readable, Purchaseable {
    private String title;
    private double price;
    private int issueNumber;
    private String month;
    private int pages;

    public Magazine(String title, int pages, double price,
                 int issueNumber, String month) {

        this.title = title;
        this.price = price;
        this.issueNumber = issueNumber;
        this.month = month;
        this.pages = pages;

    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getMonth() { return month; }

    public Integer getIssueNumber() { return issueNumber; }

    public String getDescription() {
        return title + " - " + month + ", Issue #" + issueNumber;
    }

    @Override
    public String toString() {
        return getDescription() + " - $" + price;
    }

    @Override
    public String getContent() {
        return "Magazine Content: " + getDescription();
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
