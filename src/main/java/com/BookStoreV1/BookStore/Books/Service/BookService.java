package com.BookStoreV1.BookStore.Books.Service;

import com.BookStoreV1.BookStore.Books.dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Books.dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Books.exception.BookAlreadyExistsException;
import com.BookStoreV1.BookStore.Books.mapper.BookMapper;
import com.BookStoreV1.BookStore.Books.model.Book;
import com.BookStoreV1.BookStore.Books.repository.BookRepository;
import com.BookStoreV1.BookStore.Publisher.Service.PublisherService;
import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void verifyIfBookAlreadyRegistred(BookRequestDTO bookRequestDTO) {
        bookRepository.findByNome(bookRequestDTO.getNome())
                .ifPresent(duplicatedBook -> {throw new BookAlreadyExistsException(bookRequestDTO.getNome());
                });
    }


}
