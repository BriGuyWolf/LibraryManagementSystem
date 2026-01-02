package org.example;

/***
 * Formats book information into various text formats using Text Blocks
 */
public class BookFormatter {

    public static String toJson(Book book) {
        return """
            {
              "title": "%s",
              "author": "%s",
              "pages": %d,
              "price": %.2f,
              "category": "%s"
            }
            """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice(),
                book.getCategory()
        );
    }

    /**
     * Formats a book as HTML card using text blocks.
     */
    public static String toHtmlCard(Book book) {
        return """
            <div class="book-card">
              <h2>%s</h2>
              <p class="author">by %s</p>
              <div class="details">
                <span class="pages">%d pages</span>
                <span class="price">$%.2f</span>
                <span class="category">%s</span>
              </div>
              <p class="recommendation">%s</p>
            </div>
            """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice(),
                book.getCategory(),
                book.getRecommendation()
        );
    }

    /**
     * Formats a book as a detailed report using text blocks.
     */
    public static String toReport(Book book) {
        return """
            ========================================
            BOOK REPORT
            ========================================
            Title:          %s
            Author:         %s
            Pages:          %d
            Price:          $%.2f
            Category:       %s
            Difficulty:     %s
            Reading Time:   %d minutes
            ----------------------------------------
            Recommendation:
            %s
            ========================================
            """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice(),
                book.getCategory(),
                book.getDifficulty(),
                book.getReadingTimeMinutes(),
                book.getRecommendation()
        );
    }

    /**
     * Generates SQL INSERT statement using text blocks.
     */
    public static String toSqlInsert(Book book) {
        return """
            INSERT INTO books (title, author, pages, price, category)
            VALUES ('%s', '%s', %d, %.2f, '%s');
            """.formatted(
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice(),
                book.getCategory()
        );
    }


}
