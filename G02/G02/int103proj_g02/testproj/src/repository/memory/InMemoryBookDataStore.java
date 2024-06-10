package repository.memory;

import domain.Book;
import repository.BookDataStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class InMemoryBookDataStore implements BookDataStore {

    private final Map<Integer, Book> books = new HashMap<>();
    private final Set<Integer> bookIds = new HashSet<>();


    @Override
    public void saveBook(Book book) {
        if (!bookIds.contains(book.getBookId())) {
            books.put(book.getBookId(), book);
            bookIds.add(book.getBookId());
        } else {
            System.out.println("Cannot add book. Book ID already exists.");
        }
    }

    @Override
    public Book loadBook(int bookId) {
        return null;
    }

    @Override
    public List<Book> loadAllBooks() {
        List<Book> bookList = new ArrayList<>(books.values());
        bookList.sort(Comparator.comparingInt(Book::getBookId));
        return bookList;
    }

    @Override
    public boolean deleteBook(int bookId) {
        return books.remove(bookId) != null;
    }

    @Override
    public boolean updateBook(Book book) {
        if (books.containsKey(book.getBookId())) {
            books.put(book.getBookId(), book);
            return true;
        }
        return false;
    }

    @Override
    public Book loadBookById(int bookIdToEdit) {
        return books.get(bookIdToEdit);
    }

}