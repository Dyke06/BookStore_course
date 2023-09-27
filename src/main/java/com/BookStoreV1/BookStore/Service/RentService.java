package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Mapper.RentMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RentService {

    private final RentMapper rentMapper = RentMapper.INSTANCE;

    private RentRepository rentRepository;

    private BookService bookService;

    private UserService userService;

    public RentResponseDTO create(RentRequestDTO rentRequestDTO){
        User foundUser = userService.verifyAndGetUser(rentRequestDTO.getUserId());
        Book foundBook = bookService.verifyAndGetIfExists(rentRequestDTO.getBookId());
        Rent rentToSave = rentMapper.toModel(rentRequestDTO);
        rentToSave.setUser(foundUser);
        rentToSave.setBook(foundBook);
        Rent savedRent = rentRepository.save(rentToSave);

        return rentMapper.toDTO(savedRent);
    }
}
