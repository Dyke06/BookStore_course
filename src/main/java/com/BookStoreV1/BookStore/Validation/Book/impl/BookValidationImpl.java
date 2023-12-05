package com.BookStoreV1.BookStore.Validation.Book.impl;

import com.BookStoreV1.BookStore.Dto.RequestBookDTO;
import com.BookStoreV1.BookStore.Repository.BookRepository;
import com.BookStoreV1.BookStore.Validation.Book.BookAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Book.BookLaunchExcption;
import com.BookStoreV1.BookStore.Validation.Book.BookNameAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Book.BookStockAlreayException;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class BookValidationImpl {
    private static BookRepository bookRepository;

    public BookValidationImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public static void BookValidation(RequestBookDTO requestBookDTO) {
        VerifyStock(requestBookDTO);
        veirfyValidationDate(requestBookDTO);
        verifyIfBookAlreadyRegistred(requestBookDTO);
    }

    public static void VerifyStock(RequestBookDTO requestBookDTO) throws BookStockAlreayException {
        if (requestBookDTO.getAmount() == 0 || requestBookDTO.getAmount() < 0) {
            throw new BookStockAlreayException(requestBookDTO);
        }
    }

    public static void veirfyValidationDate(RequestBookDTO requestBookDTO) {
        if (!isValidYear(requestBookDTO.getLaunch())) {
            try {
                throw new BookLaunchExcption(requestBookDTO);
            } catch (BookLaunchExcption e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void  verifyIfBookAlreadyRegistred(RequestBookDTO requestBookDTO) {
        bookRepository.findByName(requestBookDTO.getName())
                .ifPresent(duplicatedBook -> {throw new BookAlreadyExistsException(requestBookDTO.getName());
                });
    }

    public static boolean isValidYear(Integer year) {
        if (year == null) {
            return false;
        }
        int currentYear = Year.now().getValue();
        return year >= 1000 && year <= currentYear;
    }

    public static void verifyAlreadyName(Long id, RequestBookDTO requestBookDTO) {
        if (bookRepository.existsByNameAndIdNot(requestBookDTO.getName(), id)) {
            throw new BookNameAlreadyExistsException();
        }
    }

}
