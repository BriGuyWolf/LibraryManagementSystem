package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookSummary Record Tests")
public class BookSummaryTest {
    @Test
    @DisplayName("Should create book summary with all fields")
    void shouldCreateBookSummary() {
        BookSummary summary = new BookSummary("1984", "George Orwell", 328, 15.99);

        assertEquals("1984", summary.title());
        assertEquals("George Orwell", summary.author());
        assertEquals(328, summary.pages());
        assertEquals(15.99, summary.price());
    }

    @Test
    @DisplayName("Should format for display correctly")
    void shouldFormatForDisplay() {
        BookSummary summary = new BookSummary("1984", "George Orwell", 328, 15.99);

        assertEquals("'1984' by George Orwell - $15.99", summary.formatForDisplay());
    }

    @Test
    @DisplayName("Should identify expensive books")
    void shouldIdentifyExpensiveBooks() {
        BookSummary cheap = new BookSummary("Cheap Book", "Author", 100, 10.00);
        BookSummary expensive = new BookSummary("Expensive Book", "Author", 100, 50.00);

        assertFalse(cheap.isExpensive());
        assertTrue(expensive.isExpensive());
    }

    @Test
    @DisplayName("Should create from Book object")
    void shouldCreateFromBook() {
        Book book = new Book("1984", "George Orwell", 328, 15.99);
        BookSummary summary = BookSummary.fromBook(book);

        assertEquals(book.getTitle(), summary.title());
        assertEquals(book.getAuthor(), summary.author());
        assertEquals(book.getPages(), summary.pages());
        assertEquals(book.getPrice(), summary.price());
    }

    @Test
    @DisplayName("Records should have automatic equals")
    void shouldHaveAutoEquals() {
        BookSummary summary1 = new BookSummary("1984", "George Orwell", 328, 15.99);
        BookSummary summary2 = new BookSummary("1984", "George Orwell", 328, 15.99);

        assertEquals(summary1, summary2);
        assertNotSame(summary1, summary2);
    }

    @Test
    @DisplayName("Records should have automatic hashCode")
    void shouldHaveAutoHashCode() {
        BookSummary summary1 = new BookSummary("1984", "George Orwell", 328, 15.99);
        BookSummary summary2 = new BookSummary("1984", "George Orwell", 328, 15.99);

        assertEquals(summary1.hashCode(), summary2.hashCode());
    }

    @Test
    @DisplayName("Should throw exception for blank title")
    void shouldThrowExceptionForBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BookSummary("", "Author", 100, 10.00);
        });
    }

}
