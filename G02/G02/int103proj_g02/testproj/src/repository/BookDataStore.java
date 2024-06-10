package repository;

import domain.Book;

import java.util.List;

public interface BookDataStore {
    void saveBook(Book book);
    Book loadBook(int bookId);
    List<Book> loadAllBooks();
    boolean deleteBook(int bookId);

    boolean updateBook(Book book);

    Book loadBookById(int bookIdToEdit);
}
