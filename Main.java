import java.util.Scanner;

/**
 * Main class - Entry point for the Library Management System
 * Provides an interactive menu-driven interface for library operations
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a new library
        Library library = new Library("City Central Library");

        System.out.println("Welcome to " + library.getLibraryName());
        System.out.println("=========================================");

        // Load existing books from file if available
        System.out.println("Loading books from file...");
        library.loadBooksFromFile();

        // Add sample books if library is empty
        if (library.getTotalBooks() == 0) {
            addSampleBooks(library);
        }

        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice (1-9): ");

            switch (choice) {
                case 1:
                    addBook(library);
                    break;
                case 2:
                    removeBook(library);
                    break;
                case 3:
                    searchBooks(library);
                    break;
                case 4:
                    checkoutBook(library);
                    break;
                case 5:
                    returnBook(library);
                    break;
                case 6:
                    displayBooks(library);
                    break;
                case 7:
                    displayLibraryStats(library);
                    break;
                case 8:
                    saveBooks(library);
                    break;
                case 9:
                    running = false;
                    saveBooks(library); // Auto-save before exit
                    System.out.println("Thank you for using " + library.getLibraryName() + "!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Display the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Books");
        System.out.println("4. Checkout Book");
        System.out.println("5. Return Book");
        System.out.println("6. Display Books");
        System.out.println("7. Show Statistics");
        System.out.println("8. Save Books to File");
        System.out.println("9. Save & Exit");
        System.out.println("===============================================");
    }

    /**
     * Add a new book to the library
     */
    private static void addBook(Library library) {
        System.out.println("\n--- Add New Book ---");

        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            System.out.println("Error: All fields are required.");
            return;
        }

        Book newBook = new Book(title, author, isbn);
        library.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    /**
     * Remove a book from the library
     */
    private static void removeBook(Library library) {
        System.out.println("\n--- Remove Book ---");

        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine().trim();

        if (isbn.isEmpty()) {
            System.out.println("Error: ISBN is required.");
            return;
        }

        library.removeBook(isbn);
    }

    /**
     * Search for books by title or author
     */
    private static void searchBooks(Library library) {
        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");

        int choice = getIntInput("Enter your choice (1-2): ");

        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("Error: Search term is required.");
            return;
        }

        switch (choice) {
            case 1:
                searchBooksByTitle(library, searchTerm);
                break;
            case 2:
                searchBooksByAuthor(library, searchTerm);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Checkout a book
     */
    private static void checkoutBook(Library library) {
        System.out.println("\n--- Checkout Book ---");

        System.out.print("Enter ISBN of book to checkout: ");
        String isbn = scanner.nextLine().trim();

        if (isbn.isEmpty()) {
            System.out.println("Error: ISBN is required.");
            return;
        }

        library.checkoutBook(isbn);
    }

    /**
     * Return a book
     */
    private static void returnBook(Library library) {
        System.out.println("\n--- Return Book ---");

        System.out.print("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine().trim();

        if (isbn.isEmpty()) {
            System.out.println("Error: ISBN is required.");
            return;
        }

        library.returnBook(isbn);
    }

    /**
     * Display books menu
     */
    private static void displayBooks(Library library) {
        System.out.println("\n--- Display Books ---");
        System.out.println("1. Display All Books");
        System.out.println("2. Display Available Books");

        int choice = getIntInput("Enter your choice (1-2): ");

        switch (choice) {
            case 1:
                library.displayAllBooks();
                break;
            case 2:
                library.displayAvailableBooks();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Save books to file
     */
    private static void saveBooks(Library library) {
        System.out.println("\n--- Save Books to File ---");

        boolean success = library.saveBooksToFile();
        if (success) {
            System.out.println("Books saved successfully!");
        } else {
            System.out.println("Failed to save books.");
        }
    }

    /**
     * Get integer input with validation
     */
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine().trim());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Add sample books to the library
     * @param library The library to add books to
     */
    private static void addSampleBooks(Library library) {
        System.out.println("--- Adding Books ---");
        
        library.addBook(new Book(
            "Effective Java",
            "Joshua Bloch",
            "978-0-13-468599-1"
        ));
        
        library.addBook(new Book(
            "Clean Code",
            "Robert C. Martin",
            "978-0-13-235088-4"
        ));
        
        library.addBook(new Book(
            "Design Patterns",
            "Gang of Four",
            "978-0-20163-361-0"
        ));
        
        library.addBook(new Book(
            "Harry Potter and the Sorcerer's Stone",
            "J.K. Rowling",
            "978-0-43936-213-5"
        ));
        
        library.addBook(new Book(
            "The Pragmatic Programmer",
            "Andrew Hunt",
            "978-0-20161-622-4"
        ));
        
        System.out.println();
    }
    
    /**
     * Search and display books by title
     * @param library The library to search in
     * @param title The title to search for
     */
    private static void searchBooksByTitle(Library library, String title) {
        System.out.println("Searching for books with title containing: " + title);
        var results = library.findBooksByTitle(title);
        
        if (results.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            System.out.println("Found " + results.size() + " book(s):");
            for (Book book : results) {
                System.out.println("  - " + book);
            }
        }
        System.out.println();
    }
    
    /**
     * Search and display books by author
     * @param library The library to search in
     * @param author The author to search for
     */
    private static void searchBooksByAuthor(Library library, String author) {
        System.out.println("Searching for books by author: " + author);
        var results = library.findBooksByAuthor(author);
        
        if (results.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("Found " + results.size() + " book(s):");
            for (Book book : results) {
                System.out.println("  - " + book);
            }
        }
        System.out.println();
    }
    
    /**
     * Display library statistics
     * @param library The library to display stats for
     */
    private static void displayLibraryStats(Library library) {
        System.out.println("\n--- Library Statistics ---");
        System.out.println("Total books: " + library.getTotalBooks());
        System.out.println("Available books: " + library.getAvailableBookCount());
        System.out.println("Checked out books: " + 
            (library.getTotalBooks() - library.getAvailableBookCount()));
    }
}
