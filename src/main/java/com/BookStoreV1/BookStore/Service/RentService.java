package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Mapper.RentMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Rent.DataAluguelInvalidException;
import com.BookStoreV1.BookStore.Validation.Rent.RentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RentService {

    private final RentMapper rentMapper = RentMapper.INSTANCE;

    private RentRepository rentRepository;

    private BookService bookService;

    private UserService userService;

    public RentResponseDTO create(RentRequestDTO rentRequestDTO) {
        User foundUser = userService.verifyAndGetUser(rentRequestDTO.getUserId());
        Book foundBook = bookService.verifyAndGetIfExists(rentRequestDTO.getBookId());

        verifyDataDevolucaoIsNull(rentRequestDTO.getData_devolucao());
        VerifyDataAluguel(rentRequestDTO);

        Rent rentToSave = rentMapper.toModel(rentRequestDTO);
        rentToSave.setUser(foundUser);
        rentToSave.setBook(foundBook);

        Rent savedRent = rentRepository.save(rentToSave);
        RentResponseDTO rentResponseDTO = rentMapper.toDTO(savedRent);

        return rentResponseDTO;
    }



    public List<RentResponseDTO> findALL(){
        return rentRepository.findAll()
                .stream()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RentResponseDTO findById(Long rentId){
        return rentRepository.findById(rentId)
                .map(rentMapper::toDTO)
                .orElseThrow(() -> new RentNotFoundException(rentId));
    }

    public void delete(Long rentId){
        Rent FoundRent = rentRepository.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));
        rentRepository.deleteById(FoundRent.getId());
    }

    public void verifyDataDevolucaoIsNull(LocalDate dataDevolucao) {
        if (dataDevolucao != null) {
            throw new IllegalArgumentException();
        }
    }

    private static void VerifyDataAluguel(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguelHoje = LocalDate.now();
        LocalDate dataAluguel = rentRequestDTO.getData_aluguel();
        if (!dataAluguel.isEqual(dataAluguelHoje)) {
            throw new DataAluguelInvalidException(rentRequestDTO);
        }
    }
}
