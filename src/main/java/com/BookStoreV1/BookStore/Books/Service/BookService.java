package com.BookStoreV1.BookStore.Books.Service;

import com.BookStoreV1.BookStore.Books.dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Books.dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Books.exception.BookAlreadyExistsException;
import com.BookStoreV1.BookStore.Books.exception.BookNotFoundExeption;
import com.BookStoreV1.BookStore.Books.mapper.BookMapper;
import com.BookStoreV1.BookStore.Books.model.Book;
import com.BookStoreV1.BookStore.Books.repository.BookRepository;
import com.BookStoreV1.BookStore.Publisher.Service.PublisherService;
import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private PublisherService publisherService;

    public BookResponseDTO create(BookRequestDTO bookRequestDTO){
        verifyIfBookAlreadyRegistred(bookRequestDTO);
        Publisher foundPublisher = publisherService.verifyGetIfExists(bookRequestDTO.getPublisherId());

       Book bookToSave = bookMapper.toModel(bookRequestDTO);
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

    public BookResponseDTO findById(Long bookId){
        return bookRepository.findById(bookId)
                .map(bookMapper::toDTO)
                .orElseThrow(() -> new BookNotFoundExeption(bookId));
    }

    public void delete(Long bookId){
        Book foundBookDelete = verifyAndGetIfExists(bookId);
        bookRepository.deleteById(foundBookDelete.getId());
    }

    public BookResponseDTO update(Long bookId, BookRequestDTO bookRequestDTO){
        Book foundBook = verifyAndGetIfExists(bookId);
        Publisher foundPublisher = publisherService.verifyGetIfExists(bookRequestDTO.getPublisherId());

        Book bookUpdate = bookMapper.toModel(bookRequestDTO);
        bookUpdate.setId(foundBook.getId());
        bookUpdate.setPublisher(foundPublisher);
        Book updateBook = bookRepository.save(bookUpdate);
        return bookMapper.toDTO(updateBook);
    }

    private Book verifyAndGetIfExists(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundExeption(bookId));
    }

    private void verifyIfBookAlreadyRegistred(BookRequestDTO bookRequestDTO) {
        bookRepository.findByNome(bookRequestDTO.getNome())
                .ifPresent(duplicatedBook -> {throw new BookAlreadyExistsException(bookRequestDTO.getNome());
                });
    }




}
