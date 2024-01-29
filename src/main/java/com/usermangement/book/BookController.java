package com.usermangement.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookservice) {
        this.bookService = bookservice;
    }

    @GetMapping("")
    public List<Book> getBooks(@RequestParam("name") Optional<String> name) {
        return bookService.getBooks(name);
    }

    @PostMapping("")
    public Book createBook(@Validated @RequestBody BookCreateRequest request) {
        return bookService.createBook(request);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @Validated @RequestBody BookUpdateRequest request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "Book " + id + " has deleted.";
    }
}

record BookCreateRequest(
        @Size(min = 3, max = 40)
        @NotNull
        String name,
        @NotNull
        String author) {
}

record BookUpdateRequest(
        @Size(min = 3, max = 40)
        @NotNull
        String name) {
}