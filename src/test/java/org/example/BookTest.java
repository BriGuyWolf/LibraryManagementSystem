package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book Class Tests")
class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        // This runs BEFORE each test method
        // Creates a fresh Book instance for each test
        book = new Book("1984", "George Orwell", 328, 15.99);
    }


    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests{

        @Test
        @DisplayName("Should create book with all correct properties")
        void shouldCreateBookWithAllProperties() {
            assertAll("book properties",
                    () -> assertEquals("1984", book.getTitle()),
                    () -> assertEquals("George Orwell", book.getAuthor()),
                    () -> assertEquals(328, book.getPages()),
                    () -> assertEquals(15.99, book.getPrice())
            );
        }

        @Test
        @DisplayName("Should create book with different values")
        void shouldCreateDifferentBook() {
            Book anotherBook = new Book("The Hobbit", "J.R.R. Tolkien", 310, 14.99);

            assertAll("another book properties",
                    () -> assertEquals("The Hobbit", anotherBook.getTitle()),
                    () -> assertEquals("J.R.R. Tolkien", anotherBook.getAuthor()),
                    () -> assertEquals(310, anotherBook.getPages()),
                    () -> assertEquals(14.99, anotherBook.getPrice())
            );
        }
    }

    @Nested
    @DisplayName("Getter Method Tests")
    class GetterTests {

        @Test
        @DisplayName("Should return correct title")
        void shouldReturnCorrectTitle() {
            assertEquals("1984", book.getTitle());
        }

        @Test
        @DisplayName("Should return correct author")
        void shouldReturnCorrectAuthor() {
            assertEquals("George Orwell", book.getAuthor());
        }

        @Test
        @DisplayName("Should return correct number of pages")
        void shouldReturnCorrectPages() {
            assertEquals(328, book.getPages());
        }

        @Test
        @DisplayName("Should return correct price")
        void shouldReturnCorrectPrice() {
            assertEquals(15.99, book.getPrice(), 0.001);
            // Third parameter is delta for floating point comparison
        }
    }

    @Nested
    @DisplayName("Price Setter Tests")
    class SetPriceTests {

        @Test
        @DisplayName("Should update price when valid")
        void shouldUpdatePriceWhenValid() {
            // ACT
            book.setPrice(19.99);

            // ASSERT
            assertEquals(19.99, book.getPrice(), 0.001);
        }

        @Test
        @DisplayName("Should not change price when negative value provided")
        void shouldNotChangePriceWhenNegative() {
            // ARRANGE
            double originalPrice = book.getPrice();

            // ACT
            book.setPrice(-5.00);

            // ASSERT - price should remain unchanged
            assertEquals(originalPrice, book.getPrice(), 0.001);
        }

        @Test
        @DisplayName("Should accept zero price")
        void shouldAcceptZeroPrice() {
            // ACT
            book.setPrice(0.0);

            // ASSERT
            assertEquals(0.0, book.getPrice(), 0.001);
        }

        @Test
        @DisplayName("Should handle very large prices")
        void shouldHandleVeryLargePrices() {
            // ACT
            book.setPrice(999999.99);

            // ASSERT
            assertEquals(999999.99, book.getPrice(), 0.001);
        }
    }

    @Nested
    @DisplayName("Description Tests")
    class DescriptionTests {

        @Test
        @DisplayName("Should return formatted description")
        void shouldReturnFormattedDescription() {
            // ACT
            String description = book.getDescription();

            // ASSERT
            assertEquals("'1984' by George Orwell (328 pages)", description);
        }

        @Test
        @DisplayName("Description should contain title")
        void descriptionShouldContainTitle() {
            String description = book.getDescription();
            assertTrue(description.contains("1984"));
        }

        @Test
        @DisplayName("Description should contain author")
        void descriptionShouldContainAuthor() {
            String description = book.getDescription();
            assertTrue(description.contains("George Orwell"));
        }

        @Test
        @DisplayName("Description should contain page count")
        void descriptionShouldContainPageCount() {
            String description = book.getDescription();
            assertTrue(description.contains("328"));
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {

        @Test
        @DisplayName("toString should include description and price")
        void toStringShouldIncludeDescriptionAndPrice() {
            // ACT
            String result = book.toString();

            // ASSERT
            assertAll("toString contains all info",
                    () -> assertTrue(result.contains("1984")),
                    () -> assertTrue(result.contains("George Orwell")),
                    () -> assertTrue(result.contains("328")),
                    () -> assertTrue(result.contains("15.99"))
            );
        }

        @Test
        @DisplayName("toString should not be null")
        void toStringShouldNotBeNull() {
            assertNotNull(book.toString());
        }

        @Test
        @DisplayName("toString should not be empty")
        void toStringShouldNotBeEmpty() {
            String result = book.toString();
            assertFalse(result.isEmpty());
            assertTrue(result.length() > 0);
        }
    }

    @Nested
    @DisplayName("Readable Interface Tests")
    class ReadableInterfaceTests {

        @Test
        @DisplayName("Should calculate reading time correctly")
        void shouldCalculateReadingTimeCorrectly() {
            // 328 pages * 250 words/page / 200 words/minute = 410 minutes
            int expectedTime = 410;

            assertEquals(expectedTime, book.getReadingTimeMinutes());
        }

        @Test
        @DisplayName("Reading time should be positive")
        void readingTimeShouldBePositive() {
            assertTrue(book.getReadingTimeMinutes() > 0);
        }

        @Test
        @DisplayName("Should always be available")
        void shouldAlwaysBeAvailable() {
            assertTrue(book.isAvailable());
        }

        @Test
        @DisplayName("getContent should not be null")
        void getContentShouldNotBeNull() {
            assertNotNull(book.getContent());
        }

        @Test
        @DisplayName("getContent should contain book information")
        void getContentShouldContainBookInfo() {
            String content = book.getContent();
            assertTrue(content.contains("Book Content"));
        }
    }

    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCaseTests {

        @Test
        @DisplayName("Should handle book with one page")
        void shouldHandleBookWithOnePage() {
            Book shortBook = new Book("Haiku", "Poet", 1, 0.99);

            assertEquals(1, shortBook.getPages());
            assertEquals(1, shortBook.getReadingTimeMinutes()); // (1 * 250) / 200 = 1
        }

        @Test
        @DisplayName("Should handle book with very long title")
        void shouldHandleVeryLongTitle() {
            String longTitle = "A".repeat(200); // 200 character title
            Book longTitleBook = new Book(longTitle, "Author", 100, 10.0);

            assertEquals(longTitle, longTitleBook.getTitle());
        }

        @Test
        @DisplayName("Should handle book with special characters in title")
        void shouldHandleSpecialCharactersInTitle() {
            Book specialBook = new Book("Book: Part 1 - The \"Beginning\"", "Author", 100, 10.0);

            assertTrue(specialBook.getTitle().contains("\""));
            assertTrue(specialBook.getTitle().contains(":"));
        }

        @Test
        @DisplayName("Two books with same properties should not be the same object")
        void twoBooksWithSamePropertiesShouldNotBeSameObject() {
            Book book1 = new Book("1984", "George Orwell", 328, 15.99);
            Book book2 = new Book("1984", "George Orwell", 328, 15.99);

            // Different objects in memory
            assertNotSame(book1, book2);

            // But have same values
            assertEquals(book1.getTitle(), book2.getTitle());
            assertEquals(book1.getAuthor(), book2.getAuthor());
        }
    }



}