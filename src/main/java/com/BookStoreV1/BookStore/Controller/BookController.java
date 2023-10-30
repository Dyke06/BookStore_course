package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Service.BookService;
import com.BookStoreV1.BookStore.Dto.RequestBookDTO;
import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
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
    public BookResponseDTO create(@RequestBody @Valid RequestBookDTO requestBookDTO) {
        return bookService.create(requestBookDTO);
    }
    @GetMapping("/{id}")
    public BookResponseDTO findById(@PathVariable Long id) {
        return bookService.findById(id);
    }
    @GetMapping
    public List<BookResponseDTO> findAll() {
        return bookService.findAll();
    }
    @GetMapping("/most-rented")
    public List<BookResponseDTO> findMostRentedBooks() {
        List<BookResponseDTO> mostRentedBooks = bookService.findMostRentedBooks();
        return mostRentedBooks;
    }
    @PutMapping("/{id}")
    public BookResponseDTO update(@PathVariable Long id, @RequestBody @Valid RequestBookDTO requestBookDTO) {
        return bookService.update(id, requestBookDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
