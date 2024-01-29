package com.usermangement.book;

import com.usermangement.exception.DuplicationException;
import com.usermangement.exception.InternalServiceException;
import com.usermangement.exception.NotFoundException;
import com.usermangement.mail.MailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final MailService mailService;
    private final List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Java 101", "Mc", true),
            new Book(2, "Introduction of computer programming", "Campbell", false),
            new Book(3, "Clean Code", "Robert", true)
    ));

    public BookService(@Qualifier("googleMailService") MailService mailService) {
        this.mailService = mailService;
    }

    public List<Book> getBooks(Optional<String> name) {
        try {
            // for check internal server error
            // callNormalService();
            if (name.isPresent()) {
                return books.stream().filter(b -> b.getName().contains(name.get())).toList();
            }
            return books;
        } catch (Exception e) {
            throw new InternalServiceException("Internal service exception with Normal service");
        }
    }

    public Book createBook(BookCreateRequest request) {
        // Check duplicate
        Optional<Book> duplicateBook = books.stream().filter(b -> b.getName().equals(request.name())).findFirst();
        if (duplicateBook.isPresent()) {
            throw new DuplicationException("Book name " + request.name() + " is already exist");
        }

        // Find max id
        Optional<Integer> maxId = books.stream().map(b -> b.getId()).max(Integer::compareTo);
        Integer currentId = maxId.orElse(0) + 1;
        Book book = new Book(currentId, request.name(), request.author(), true);
        books.add(book);

        mailService.sendMail("admin@book.com", "Book has created.");
        return book;
    }

    public Book getBookById(Integer id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Book not found by id " + id));
    }

    public Book updateBook(Integer id, BookUpdateRequest request) {
        Book book = getBookById(id);

        book.setName(request.name());
        return book;
    }

    public void deleteBook(Integer id) {
        Book book = getBookById(id);
        books.remove(book);
    }

    // This method for always throw Run time
    private void callNormalService() {
        throw new RuntimeException();
    }
}
