/**
 * Book class represents a book in the library system
 */
public class Book {
    // Instance variables
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    
    /**
     * Constructor to create a new Book
     * @param title The title of the book
     * @param author The author of the book
     * @param isbn The ISBN of the book
     */
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }
    
    // Getter methods
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    /**
     * Method to checkout (borrow) the book
     * @return true if book was available and successfully checked out, false otherwise
     */
    public boolean checkout() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }
    
    /**
     * Method to return the book
     */
    public void returnBook() {
        isAvailable = true;
    }
    
    /**
     * Returns a string representation of the book
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
