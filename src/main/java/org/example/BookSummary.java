package org.example;

public record BookSummary(
        String title,
        String author,
        int pages,
        double price
) {
    // Compact constructor - for validation
    public BookSummary {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank");
        }
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    // Custom methods can still be added
    public String formatForDisplay() {
        return String.format("'%s' by %s - $%.2f", title, author, price);
    }

    public boolean isExpensive() {
        return price > 30.00;
    }

    // Static factory method
    public static BookSummary fromBook(Book book) {
        return new BookSummary(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice()
        );
    }
}
