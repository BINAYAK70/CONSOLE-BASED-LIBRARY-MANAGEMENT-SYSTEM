import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
/**
 * Library class manages a collection of books
 */
public class Library {
    // Instance variables
    private String libraryName;
    private List<Book> books;
    
    /**
     * Constructor to create a new Library
     * @param libraryName The name of the library
     */
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
    }
    
    /**
     * Get the library name
     * @return The name of the library
     */
    public String getLibraryName() {
        return libraryName;
    }
    
    /**
     * Set the library name
     * @param libraryName The new name for the library
     */
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
    
    /**
     * Add a book to the library
     * @param book The book to add
     */
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }
    }
    
    /**
     * Remove a book from the library by ISBN
     * @param isbn The ISBN of the book to remove
     * @return true if book was found and removed, false otherwise
     */
    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                Book removedBook = books.remove(i);
                System.out.println("Book removed: " + removedBook.getTitle());
                return true;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
        return false;
    }
    
    /**
     * Find a book by ISBN
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    /**
     * Find books by title (partial match)
     * @param title The title to search for
     * @return List of books matching the title
     */
    public List<Book> findBooksByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
    
    /**
     * Find books by author (partial match)
     * @param author The author to search for
     * @return List of books by the author
     */
    public List<Book> findBooksByAuthor(String author) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
    
    /**
     * Display all books in the library
     */
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        System.out.println("\n=== Books in " + libraryName + " ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
    
    /**
     * Display all available books
     */
    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books in " + libraryName + " ===");
        boolean hasAvailableBooks = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                hasAvailableBooks = true;
            }
        }
        if (!hasAvailableBooks) {
            System.out.println("No available books at the moment.");
        }
    }
    
    /**
     * Checkout a book by ISBN
     * @param isbn The ISBN of the book to checkout
     * @return true if checkout was successful, false otherwise
     */
    public boolean checkoutBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return false;
        }
        
        if (book.checkout()) {
            System.out.println("Successfully checked out: " + book.getTitle());
            return true;
        } else {
            System.out.println("Book is not available: " + book.getTitle());
            return false;
        }
    }
    
    /**
     * Return a book by ISBN
     * @param isbn The ISBN of the book to return
     * @return true if return was successful, false otherwise
     */
    public boolean returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return false;
        }
        
        book.returnBook();
        System.out.println("Successfully returned: " + book.getTitle());
        return true;
    }
    
    /**
     * Get the total number of books in the library
     * @return The number of books
     */
    public int getTotalBooks() {
        return books.size();
    }
    
    /**
     * Get the number of available books
     * @return The number of available books
     */
    public int getAvailableBookCount() {
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Save all books to a text file
     * @param filename The name of the file to save to (default: books.txt)
     * @return true if save was successful, false otherwise
     */
    public boolean saveBooksToFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            filename = "books.txt";
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write header
            writer.write("=".repeat(80));
            writer.newLine();
            writer.write("Library: " + libraryName);
            writer.newLine();
            writer.write("Total Books: " + books.size());
            writer.newLine();
            writer.write("Available Books: " + getAvailableBookCount());
            writer.newLine();
            writer.write("=".repeat(80));
            writer.newLine();
            writer.newLine();
            
            // Write each book's information
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                writer.write("Book #" + (i + 1));
                writer.newLine();
                writer.write("-".repeat(40));
                writer.newLine();
                writer.write("Title: " + book.getTitle());
                writer.newLine();
                writer.write("Author: " + book.getAuthor());
                writer.newLine();
                writer.write("ISBN: " + book.getIsbn());
                writer.newLine();
                writer.write("Status: " + (book.isAvailable() ? "Available" : "Checked Out"));
                writer.newLine();
                writer.newLine();
            }
            
            writer.write("=".repeat(80));
            writer.newLine();
            writer.write("End of Library Records");
            writer.newLine();
            
            System.out.println("Books successfully saved to " + filename);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Save all books to the default file (books.txt)
     * @return true if save was successful, false otherwise
     */
    public boolean saveBooksToFile() {
        return saveBooksToFile("books.txt");
    }

    /**
     * Load books from a text file and add them to the library
     * @param filename The name of the file to load from (default: books.txt)
     * @return true if load was successful, false otherwise
     */
    public boolean loadBooksFromFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            filename = "books.txt";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean readingBooks = false;
            String currentTitle = null;
            String currentAuthor = null;
            String currentIsbn = null;
            String currentStatus = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip header until we find book entries
                if (line.startsWith("Book #")) {
                    readingBooks = true;
                    // Reset current book data for new book
                    currentTitle = null;
                    currentAuthor = null;
                    currentIsbn = null;
                    currentStatus = null;
                } else if (readingBooks && !line.isEmpty()) {
                    if (line.startsWith("Title:")) {
                        currentTitle = line.substring(6).trim();
                    } else if (line.startsWith("Author:")) {
                        currentAuthor = line.substring(7).trim();
                    } else if (line.startsWith("ISBN:")) {
                        currentIsbn = line.substring(5).trim();
                    } else if (line.startsWith("Status:")) {
                        currentStatus = line.substring(7).trim();

                        // Create book when we have all required fields
                        if (currentTitle != null && currentAuthor != null && currentIsbn != null && currentStatus != null) {
                            Book book = new Book(currentTitle, currentAuthor, currentIsbn);
                            if ("Checked Out".equals(currentStatus)) {
                                book.setAvailable(false);
                            }
                            addBook(book);
                        }
                    }
                }

                // Stop reading when we reach the end marker
                if (line.equals("End of Library Records")) {
                    break;
                }
            }

            System.out.println("Books successfully loaded from " + filename);
            return true;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return false;
        } catch (IOException e) {
            System.err.println("Error loading books from file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Load books from the default file (books.txt)
     * @return true if load was successful, false otherwise
     */
    public boolean loadBooksFromFile() {
        return loadBooksFromFile("books.txt");
    }
}
