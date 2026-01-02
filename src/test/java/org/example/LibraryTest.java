package org.example;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book shortBook;
    private Magazine magazine1;

    @BeforeEach
    void setUp() {
        // Create fresh library before each test
        library = new Library("Test Library");

        // Create test books
        book1 = new Book("1984", "George Orwell", 328, 15.99);
        book2 = new Book("The Hobbit", "J.R.R. Tolkien", 310, 14.99);
        book3 = new Book("Clean Code", "Robert Martin", 464, 39.99);
        shortBook = new Book("Short Book", "Brian Wolf", 100, 10.99);
        // Create test magazine
        magazine1 = new Magazine("PC Magazine", 172, 6.99, 124, "December 2025");
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test (optional but good practice)
        library = null;
        book1 = null;
        book2 = null;
        book3 = null;
        shortBook = null;
        magazine1 = null;
    }

    @Nested
    @DisplayName("Initialization Tests")
    class InitializationTests {

        @Test
        @DisplayName("Should create empty library")
        void shouldCreateEmptyLibrary() {
            assertEquals(0, library.getAllReadableMaterial().size());
        }

        @Test
        @DisplayName("Should have correct library name")
        void shouldHaveCorrectName() {
            // Note: If your Library class doesn't have getName(), skip this test
            // or add the method to Library first
            assertEquals("Test Library", library.getName());
        }

        @Test
        @DisplayName("getAllBooks should not return null")
        void getAllBooksShouldNotReturnNull() {
            assertNotNull(library.getAllReadableMaterial());
        }
    }

    @Nested
    @DisplayName("Add Book Tests")
    class AddBookTests {

        @Test
        @DisplayName("Should add single book successfully")
        void shouldAddSingleBook() {
            // ACT
            library.addReadableMaterial(book1);

            // ASSERT
            assertEquals(1, library.getAllReadableMaterial().size());
            assertTrue(library.getAllReadableMaterial().contains(book1));
        }

        @Test
        @DisplayName("Should add multiple books")
        void shouldAddMultipleBooks() {
            // ACT
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            library.addReadableMaterial(book3);

            // ASSERT
            assertEquals(3, library.getAllReadableMaterial().size());

            assertAll("all books present",
                    () -> assertTrue(library.getAllReadableMaterial().contains(book1)),
                    () -> assertTrue(library.getAllReadableMaterial().contains(book2)),
                    () -> assertTrue(library.getAllReadableMaterial().contains(book3))
            );
        }

        @Test
        @DisplayName("Should allow duplicate books")
        void shouldAllowDuplicateBooks() {
            // ACT
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book1);  // Add same book again

            // ASSERT
            assertEquals(2, library.getAllReadableMaterial().size());
        }

        @Test
        @DisplayName("Should maintain order of added books")
        void shouldMaintainOrderOfAddedBooks() {
            // ACT
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            library.addReadableMaterial(book3);

            // ASSERT
            ArrayList<Readable> books = library.getAllReadableMaterial();
            assertSame(book1, books.get(0));
            assertSame(book2, books.get(1));
            assertSame(book3, books.get(2));
        }
    }

    @Nested
    @DisplayName("Remove Book Tests")
    class RemoveBookTests {

        @BeforeEach
        void addBooksToLibrary() {
            // Add books before each remove test
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            library.addReadableMaterial(book3);
        }

        @Test
        @DisplayName("Should remove book by title successfully")
        void shouldRemoveBookByTitle() {
            // ACT
            boolean removed = library.removeReadableMaterial("1984");

            // ASSERT
            assertTrue(removed);
            assertEquals(2, library.getAllReadableMaterial().size());
            assertFalse(library.getAllReadableMaterial().contains(book1));
        }

        @Test
        @DisplayName("Should return false when removing non-existent book")
        void shouldReturnFalseForNonExistentBook() {
            // ACT
            boolean removed = library.removeReadableMaterial("Non-existent Book");

            // ASSERT
            assertFalse(removed);
            assertEquals(3, library.getAllReadableMaterial().size());  // Size unchanged
        }

        @Test
        @DisplayName("Should remove correct book when multiple books exist")
        void shouldRemoveCorrectBook() {
            // ACT
            library.removeReadableMaterial("The Hobbit");

            // ASSERT
            ArrayList<Readable> remaining = library.getAllReadableMaterial();
            assertEquals(2, remaining.size());
            assertTrue(remaining.contains(book1));
            assertFalse(remaining.contains(book2));
            assertTrue(remaining.contains(book3));
        }

        @Test
        @DisplayName("Should handle removing from empty library")
        void shouldHandleRemovingFromEmptyLibrary() {
            // ARRANGE - create new empty library
            Library emptyLibrary = new Library("Empty");

            // ACT
            boolean removed = emptyLibrary.removeReadableMaterial("Any Book");

            // ASSERT
            assertFalse(removed);
            assertEquals(0, emptyLibrary.getAllReadableMaterial().size());
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should handle complete workflow: add, find, remove")
        void shouldHandleCompleteWorkflow() {
            // Add books
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            assertEquals(2, library.getAllReadableMaterial().size());

            // Find book
            Readable found = library.findMaterialByTitle("1984");
            assertNotNull(found);

            // Remove book
            boolean removed = library.removeReadableMaterial("1984");
            assertTrue(removed);
            assertEquals(1, library.getAllReadableMaterial().size());

            // Try to find removed book
            Readable notFound = library.findMaterialByTitle("1984");
            assertNull(notFound);
        }

        @Test
        @DisplayName("Should handle polymorphism with Books and Magazines")
        void shouldHandlePolymorphism() {
            // Add both books and magazines
            library.addReadableMaterial(book1);
            library.addReadableMaterial(magazine1);

            // ASSERT
            assertEquals(2, library.getAllReadableMaterial().size());

            // Check that both are Readable
            ArrayList<Readable> items = library.getAllReadableMaterial();
            assertTrue(items.get(0) instanceof Readable);
            assertTrue(items.get(1) instanceof Readable);

            // Check specific types using instanceof
            long bookCount = items.stream()
                    .filter(item -> item instanceof Book)
                    .count();
            long magazineCount = items.stream()
                    .filter(item -> item instanceof Magazine)
                    .count();

            assertEquals(1, bookCount);
            assertEquals(1, magazineCount);
        }
    }

    @Nested
    @DisplayName("Additional HW for Day 5")
    class AdditionalTests {
        @Test
        @DisplayName("Should return the most expensive reading material")
        void shouldReturnMostExpensiveMaterial() {
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            library.addReadableMaterial(book3);

            Optional<Purchaseable> mostExpensive = library.getMostExpensiveMaterial();

            assertTrue(mostExpensive.isPresent());
            assertEquals(book3, mostExpensive.get());

        }

        @Test
        @DisplayName("Should return the least expensive reading material")
        void shouldReturnLeastExpensiveMaterial() {
            library.addReadableMaterial(book1);
            library.addReadableMaterial(book2);
            library.addReadableMaterial(book3);
            library.addReadableMaterial(magazine1);

            Optional<Purchaseable> leastExpensive = library.getLeastExpensiveMaterial();

            assertTrue(leastExpensive.isPresent());
            assertEquals(magazine1, leastExpensive.get());

        }
    }

    @Nested
    @DisplayName("Pattern Matching Tests")
    class PatternMatchingTests {
        @Test
        @DisplayName("Should describe book with pattern matching")
        void shouldDescribeBook() {

            String description = library.getItemDescription(book1);

            assertTrue(description.startsWith("Book:"));
            assertTrue(description.contains("1984"));
            assertTrue(description.contains("George Orwell"));
            assertTrue(description.contains("328 pages"));
            assertTrue(description.contains("15.99"));
        }

        @Test
        @DisplayName("Should describe magazine with pattern matching")
        void shouldDescribeMagazine() {

            String description = library.getItemDescription(magazine1);

            assertTrue(description.startsWith("Magazine:"));
            assertTrue(description.contains("PC Magazine"));
            assertTrue(description.contains("124"));
            assertTrue(description.contains("December 2025"));
        }

        @Test
        @DisplayName("Should calculate book discount correctly")
        void shouldCalculateBookDiscount() {
            double discount = library.calculateDiscount(book3);

            assertEquals(3.999, discount, 0.01);  // 10% of $39.99
        }

        @Test
        @DisplayName("Should calculate magazine discount correctly")
        void shouldCalculateMagazineDiscount() {

            double discount = library.calculateDiscount(magazine1);

            // Assuming magazine has getPrice() - adjust based on your implementation
            assertTrue(discount > 0);
        }

        @Test
        @DisplayName("Should not discount cheap books")
        void shouldNotDiscountCheapBooks() {
            double discount = library.calculateDiscount(book1);

            assertEquals(0.0, discount, 0.01);  // No discount under $30
        }

        @Test
        @DisplayName("Should qualify short books for express shipping")
        void shouldQualifyShortBooksForExpress() {
            boolean qualifies = library.qualifiesForExpressShipping(shortBook);
            assertTrue(qualifies);  // Under 200 pages
        }

        @Test
        @DisplayName("Should not qualify long books for express shipping")
        void shouldNotQualifyLongBooks() {
            boolean qualifies = library.qualifiesForExpressShipping(book2);
            assertFalse(qualifies);  // Over 200 pages
        }

        @Test
        @DisplayName("Should qualify current magazines for express shipping")
        void shouldQualifyCurrentMagazines() {
            boolean qualifies = library.qualifiesForExpressShipping(magazine1);

            assertTrue(qualifies);  // Current year
        }
    }


}
