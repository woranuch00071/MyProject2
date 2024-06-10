import domain.*;
import org.w3c.dom.ls.LSOutput;
import repository.*;
import repository.file.FileMovieDataStore;
import repository.jdbc.JdbcTicketRepository;
import repository.jdbc.JdbcTrainRepository;
import repository.jdbc.JdbcUserRepository;
import repository.memory.InMemoryBookDataStore;
import service.ReservationService;

import java.io.Console;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Authenticator authenticator = new Authenticator();

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        Console console = System.console();
        String password = new String(console.readPassword("Enter password: "));

        if (authenticator.authenticate(username, password)) {
            System.out.println("Authentication successful.");

            System.out.println("Choose data storage type (1: In-memory, 2: File, 3: JDBC):");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                // In Memory
                case 1:
                    runInMemoryCase(scanner);
                    break;
                // File
                case 2:
                    runFileCase(scanner);
                    break;
                // Database
                case 3:
                    runJdbcCase(scanner);
                    break;
                default:
                    throw new InvalidChoiceException("Invalid choice");
            }

        } else {
            throw new InvalidChoiceException("Authentication failed.");
        }

        scanner.close();
    }

    private static void runInMemoryCase(Scanner scanner) {
        BookDataStore bookDataStore = new InMemoryBookDataStore();

        // Adding sample books
        bookDataStore.saveBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        bookDataStore.saveBook(new Book(2, "1984", "George Orwell"));
        bookDataStore.saveBook(new Book(3, "To Kill a Mockingbird", "Harper Lee"));

        while (true) {
            System.out.println(" ");
            System.out.println("Please choose your preferred option.");
            System.out.println("1. View All Books");
            System.out.println("2. Add a Book");
            System.out.println("3. Edit a Book");
            System.out.println("4. Delete a Book");
            System.out.println("5. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (userChoice) {
                case 1:
                    System.out.println();
                    for (Book book : bookDataStore.loadAllBooks()) {
                        System.out.println("BookID: " + book.getBookId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println();
                    }
                    System.out.println("---------------------------------------");
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();

                    bookDataStore.saveBook(new Book(bookId, title, author));
                    System.out.println("Book added successfully.");
                    System.out.println("----------------------------------------");
                    break;
                case 3:
                    // Edit a Book
                    System.out.print("Enter book ID to edit: ");
                    int bookIdToEdit = Integer.parseInt(scanner.nextLine());
                    Book existingBook = bookDataStore.loadBookById(bookIdToEdit);
                    if (existingBook != null) {
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        existingBook.setTitle(newTitle);
                        existingBook.setAuthor(newAuthor);
                        boolean editSuccess = bookDataStore.updateBook(existingBook);
                        if (editSuccess) {
                            System.out.println("Book updated successfully.");
                        } else {
                            System.out.println("Failed to update book.");
                        }
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter book ID to delete: ");
                    int bookIdToDelete = Integer.parseInt(scanner.nextLine());
                    boolean deleteSuccess = bookDataStore.deleteBook(bookIdToDelete);
                    if (deleteSuccess) {
                        System.out.println("Book deleted successfully.");
                    } else {
                        System.out.println("Book ID not found.");
                    }
                    System.out.println("----------------------------------------");
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void runFileCase(Scanner scanner) {
        MovieDataStore movieDataStore = new FileMovieDataStore();

        // Adding sample movies
//        movieDataStore.saveMovie(new Movie(1, "Inception", "Christopher Nolan"));
//        movieDataStore.saveMovie(new Movie(2, "The Matrix", "Lana Wachowski, Lilly Wachowski"));
//        movieDataStore.saveMovie(new Movie(3, "Interstellar", "Christopher Nolan"));

        while (true) {
            System.out.println(" ");
            System.out.println("Please choose your preferred option.");
            System.out.println("1. View All Movies");
            System.out.println("2. Add a Movie");
            System.out.println("3. Update a Movie");
            System.out.println("4. Delete a Movie");
            System.out.println("5. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (userChoice) {
                case 1:
                    System.out.println();
                    for (Movie movie : movieDataStore.loadAllMovies()) {
                        System.out.println("MovieID: " + movie.getMovieId());
                        System.out.println("Title: " + movie.getTitle());
                        System.out.println("Director: " + movie.getDirector());
                        System.out.println();
                    }
                    System.out.println("---------------------------------------");
                    break;
                case 2:
                    System.out.print("Enter movie ID: ");
                    String movieIdInput = scanner.nextLine();
                    if (movieIdInput.isEmpty()) {
                        System.out.println("Cannot add movie. Movie ID cannot be empty.");
                        break;
                    }
                    int movieId;
                    try {
                        movieId = Integer.parseInt(movieIdInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid movie ID. Please enter a valid integer.");
                        break;
                    }

                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter movie director: ");
                    String director = scanner.nextLine();

                    movieDataStore.saveMovie(new Movie(movieId, title, director));
                    System.out.println("Movie added successfully.");
                    System.out.println("----------------------------------------");
                    break;
                case 3:
                    // Edit a Movie
                    System.out.print("Enter movie ID to edit: ");
                    int movieIdToEdit = Integer.parseInt(scanner.nextLine());
                    Movie existingMovie = movieDataStore.loadMovieById(movieIdToEdit);
                    if (existingMovie != null) {
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new director: ");
                        String newDirector = scanner.nextLine();
                        existingMovie.setTitle(newTitle);
                        existingMovie.setDirector(newDirector);
                        boolean editSuccess = movieDataStore.updateMovie(existingMovie);
                        if (editSuccess) {
                            System.out.println("Movie updated successfully.");
                        } else {
                            System.out.println("Failed to update movie.");
                        }
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter movie ID to delete: ");
                    int movieIdToDelete = Integer.parseInt(scanner.nextLine());
                    boolean deleteSuccess = movieDataStore.deleteMovie(movieIdToDelete);
                    if (deleteSuccess) {
                        System.out.println("Movie deleted successfully.");
                    } else {
                        System.out.println("Movie ID not found.");
                    }
                    System.out.println("----------------------------------------");
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void runJdbcCase(Scanner scanner) {
        DataStore dataStore = DataStoreFactory.createDataStore(3);
        ReservationService service = new ReservationService(new JdbcUserRepository(), new JdbcTrainRepository(), new JdbcTicketRepository());

        System.out.println(dataStore.loadData());
        System.out.println("database template success");

        while (true) {
            System.out.println(" ");
            System.out.println("Please choose your preferred option.");
            System.out.println("1. View Trains");
            System.out.println("2. Booking Ticket");
            System.out.println("3. View Tickets");
            System.out.println("4. Cancel All Tickets");
            System.out.println("5. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (userChoice) {
                case 1:
                    String trains = service.getAllTrains();
                    System.out.println("Trains Schedule: ");
                    System.out.println(trains);
                    System.out.println("---------------------------------------");
                    break;
                case 2:
                    String availableTrains = service.getAllTrains();
                    System.out.println("Available Trains:");
                    System.out.println(availableTrains);

                    System.out.print("Select train number to book: ");
                    int trainNumberToBook = Integer.parseInt(scanner.nextLine());

                    Ticket newTicket = new Ticket();
                    newTicket.setTrainNumber(trainNumberToBook);
                    boolean addSuccess = service.bookTicket(newTicket);
                    if (!addSuccess) {
                        System.out.println(" ");
                        System.out.println("Train number not found.");
                        System.out.println(" ");
                        System.out.println("----------------------------------------");
                        break;
                    }
                    System.out.println(" ");
                    System.out.println("Ticket booked successfully.");
                    System.out.println(" ");
                    System.out.println("----------------------------------------");
                    break;
                case 3:
                    String ticket = service.getAllTickets();
                    if (ticket.isEmpty()) {
                        System.out.println(" ");
                        System.out.println("Ticket Not found");
                        System.out.println(" ");
                        System.out.println("---------------------------------------");
                        break;
                    }
                    System.out.println("Your tickets: ");
                    System.out.println(ticket);

                    break;
                case 4:
                    service.deleteTickets();
                    System.out.println(" ");
                    System.out.println("Cancel Tickets successfully.");
                    System.out.println(" ");
                    System.out.println("---------------------------------------");
                    break;

                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}