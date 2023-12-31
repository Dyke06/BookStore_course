package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Dto.RequestBookDTO;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Book.*;
import com.BookStoreV1.BookStore.Mapper.BookMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Repository.BookRepository;
import com.BookStoreV1.BookStore.Model.Publisher;
import com.BookStoreV1.BookStore.Validation.Book.impl.BookValidationImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private RentRepository rentRepository;

    private PublisherService publisherService;

    public BookResponseDTO create(RequestBookDTO requestBookDTO){
        Publisher foundPublisher = publisherService.verifyGetIfExists(requestBookDTO.getPublisherId());
        BookValidationImpl.BookValidation(requestBookDTO);

        Book bookToSave = bookMapper.toModel(requestBookDTO);
        bookToSave.setPublisher(foundPublisher);
        Book savedBook = bookRepository.save(bookToSave);
        return bookMapper.toDTO(savedBook);
    }

    public List<BookResponseDTO> findAll(){
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO findById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new BookNotFoundExeption(id));
    }

    public List<BookResponseDTO> findMostRentedBooks() {

        List<Book> books = bookRepository.findAll();

        List<Book> mostRentedBooks = books.stream()
                .sorted(Comparator.comparingInt(Book::getTotalRent).reversed())
                .collect(Collectors.toList());

        List<BookResponseDTO> mostRentedBooksDTO = mostRentedBooks.stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());

        return mostRentedBooksDTO;
    }

    public BookResponseDTO update(Long id, RequestBookDTO requestBookDTO){
        Book foundBook = verifyAndGetIfExists(id);
        BookValidationImpl.veirfyValidationDate(requestBookDTO);
        BookValidationImpl.verifyAlreadyName(id, requestBookDTO);
        BookValidationImpl.VerifyStock(requestBookDTO);
        Publisher foundPublisher = publisherService.verifyGetIfExists(requestBookDTO.getPublisherId());

        Book bookUpdate = bookMapper.toModel(requestBookDTO);
        bookUpdate.setId(foundBook.getId());
        bookUpdate.setTotalRent(foundBook.getTotalRent());
        bookUpdate.setPublisher(foundPublisher);
        Book updateBook = bookRepository.save(bookUpdate);
        return bookMapper.toDTO(updateBook);
    }

    @SneakyThrows
    public void delete(Long id){
        Book foundBookDelete = verifyAndGetIfExists(id);
        if (hasRentsForBook(id)) {
            throw new BookHasRentsException(id);
        }
        bookRepository.deleteById(foundBookDelete.getId());
    }

    public boolean hasRentsForBook(Long id) {
        List<Rent> rents = rentRepository.findByBookId(id);
        return !rents.isEmpty();
    }

    public Book verifyAndGetIfExists(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundExeption(id));
    }

    public void update(Long id, Book book) {
        Book existingBook = verifyAndGetIfExists(id);
        existingBook.setPublisher(book.getPublisher());

        bookRepository.save(existingBook);
    }

}
