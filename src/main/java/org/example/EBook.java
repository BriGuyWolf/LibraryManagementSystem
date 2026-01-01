package org.example;

public class EBook extends Book {
    private double fileSizeMB;
    private String format;

    public EBook(String title, String author, int pages, double price,
                 double fileSizeMB, String format) {

        super(title, author, pages, price);
        this.fileSizeMB = fileSizeMB;
        this.format = format;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String getDescription() {
        // Call parent's version, then add more info
        return super.getDescription() + " [" + format + ", " + fileSizeMB + "MB]";
    }

    // New Download info
    public String getDownloadInfo() {
        return "Download: " + getTitle() + " (" + format + " format, " + fileSizeMB + " MB)";
    }
}
