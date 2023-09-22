package com.BookStoreV1.BookStore.Books.controller;

import com.BookStoreV1.BookStore.Books.Service.BookService;
import com.BookStoreV1.BookStore.Books.dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Books.dto.BookResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController implements BookControllerDocs {
    @Autowired
    private BookService bookService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO create(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        return bookService.create(bookRequestDTO);
    }
    @GetMapping("/{bookId}")
    public BookResponseDTO findById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }
    @GetMapping
    public List<BookResponseDTO> findAll() {
        return bookService.findAll();
    }
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
    }
    @PutMapping("/{bookId}")
    public BookResponseDTO update(@PathVariable  Long bookId,@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        return bookService.update(bookId, bookRequestDTO);
    }
}
