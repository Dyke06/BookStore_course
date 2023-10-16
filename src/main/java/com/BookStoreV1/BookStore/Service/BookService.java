package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.RequestBookDTO;
import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Book.BookAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Book.BookHasRentsException;
import com.BookStoreV1.BookStore.Validation.Book.BookLaunchExcption;
import com.BookStoreV1.BookStore.Validation.Book.BookNotFoundExeption;
import com.BookStoreV1.BookStore.Mapper.BookMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Repository.BookRepository;
import com.BookStoreV1.BookStore.Model.Publisher;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
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
        if (!isValidYear(requestBookDTO.getLaunch())) {
            try {
                throw new BookLaunchExcption(requestBookDTO);
            } catch (BookLaunchExcption e) {
                throw new RuntimeException(e);
            }
        }
        verifyIfBookAlreadyRegistred(requestBookDTO);
        Publisher foundPublisher = publisherService.verifyGetIfExists(requestBookDTO.getPublisherId());

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

    public BookResponseDTO update(Long id, RequestBookDTO requestBookDTO){
        Book foundBook = verifyAndGetIfExists(id);
        Publisher foundPublisher = publisherService.verifyGetIfExists(requestBookDTO.getPublisherId());

        Book bookUpdate = bookMapper.toModel(requestBookDTO);
        bookUpdate.setId(foundBook.getId());
        bookUpdate.setPublisher(foundPublisher);
        Book updateBook = bookRepository.save(bookUpdate);
        return bookMapper.toDTO(updateBook);
    }

    public Book verifyAndGetIfExists(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundExeption(id));
    }

    private void verifyIfBookAlreadyRegistred(RequestBookDTO requestBookDTO) {
        bookRepository.findByName(requestBookDTO.getName())
                .ifPresent(duplicatedBook -> {throw new BookAlreadyExistsException(requestBookDTO.getName());
                });
    }

    public void update(Long id, Book book) {
        Book existingBook = verifyAndGetIfExists(id);
        existingBook.setPublisher(book.getPublisher());

        bookRepository.save(existingBook);
    }

    private boolean isValidYear(Integer year) {
        if (year == null) {
            return false;
        }
        int currentYear = Year.now().getValue();
        return year >= 1000 && year <= currentYear;
    }
}
