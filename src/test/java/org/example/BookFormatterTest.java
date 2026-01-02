package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookFormatter Text Block Tests")
class BookFormatterTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("1984", "George Orwell", 328, 15.99);
    }

    @Test
    @DisplayName("Should format book as JSON")
    void shouldFormatAsJson() {
        String json = BookFormatter.toJson(book);

        // Check that all fields are present
        assertTrue(json.contains("\"title\": \"1984\""));
        assertTrue(json.contains("\"author\": \"George Orwell\""));
        assertTrue(json.contains("\"pages\": 328"));
        assertTrue(json.contains("\"price\": 15.99"));

        // Check proper JSON structure
        assertTrue(json.trim().startsWith("{"));
        assertTrue(json.trim().endsWith("}"));
    }


    @Test
    @DisplayName("Should format book as HTML card")
    void shouldFormatAsHtmlCard() {
        String html = BookFormatter.toHtmlCard(book);

        // Check HTML structure
        assertTrue(html.contains("<div class=\"book-card\">"));
        assertTrue(html.contains("<h2>1984</h2>"));
        assertTrue(html.contains("by George Orwell"));
        assertTrue(html.contains("328 pages"));
        assertTrue(html.contains("$15.99"));
        assertTrue(html.contains("</div>"));
    }


    @Test
    @DisplayName("Should format book as detailed report")
    void shouldFormatAsReport() {
        String report = BookFormatter.toReport(book);

        // Check report contains key information
        assertTrue(report.contains("BOOK REPORT"));
        assertTrue(report.contains("Title:          1984"));
        assertTrue(report.contains("Author:         George Orwell"));
        assertTrue(report.contains("Pages:          328"));
        assertTrue(report.contains("Price:          $15.99"));
        assertTrue(report.contains("Reading Time:"));
        assertTrue(report.contains("Recommendation:"));

        // Check report has borders
        assertTrue(report.contains("========================================"));
    }

    @Test
    @DisplayName("Should generate SQL INSERT statement")
    void shouldGenerateSqlInsert() {
        String sql = BookFormatter.toSqlInsert(book);

        // Check SQL structure
        assertTrue(sql.contains("INSERT INTO books"));
        assertTrue(sql.contains("VALUES"));
        assertTrue(sql.contains("'1984'"));
        assertTrue(sql.contains("'George Orwell'"));
        assertTrue(sql.contains("328"));
        assertTrue(sql.contains("15.99"));
        assertTrue(sql.trim().endsWith(";"));
    }

    @Test
    @DisplayName("Should handle special characters in title")
    void shouldHandleSpecialCharacters() {
        Book specialBook = new Book("Book: \"Part 1\"", "Author", 100, 10.0);
        String json = BookFormatter.toJson(specialBook);

        // Should contain the title with special characters
        assertTrue(json.contains("Book: \\\"Part 1\\\"") ||
                json.contains("Book: \"Part 1\""));
    }

    @Test
    @DisplayName("JSON should be properly formatted with indentation")
    void jsonShouldBeIndented() {
        String json = BookFormatter.toJson(book);

        // Check that there are multiple lines
        assertTrue(json.split("\n").length > 1);

        // Check for proper indentation (spaces at start of lines)
        assertTrue(json.contains("  \"title\""));
    }
}
