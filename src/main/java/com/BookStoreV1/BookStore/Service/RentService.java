package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Mapper.RentMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Rent.*;
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
        verifyDataPrevisao(rentRequestDTO);

        Rent rentToSave = rentMapper.toModel(rentRequestDTO);
        rentToSave.setUser(foundUser);
        rentToSave.setBook(foundBook);

        Rent savedRent = rentRepository.save(rentToSave);

        foundBook.setQuantidade(foundBook.getQuantidade() - 1);
        bookService.update(foundBook.getId(), foundBook);

        RentResponseDTO rentResponseDTO = rentMapper.toDTO(savedRent);

        return rentResponseDTO;
    }
    public RentResponseDTO update(RentRequestDTO rentRequestDTO, Long rentId) {
        Rent existingRent = VerifyAndGetIfExists(rentId);

        User foundUser = userService.verifyAndGetUser(rentRequestDTO.getUserId());
        Book foundBook = bookService.verifyAndGetIfExists(rentRequestDTO.getBookId());

        LocalDate dataDevolucao = rentRequestDTO.getData_devolucao();
        if (dataDevolucao != null) {
            existingRent.setData_devolucao(dataDevolucao);
        } else {
            throw new ReturnDateCannotBeNull("A data de devolução não pode ser nula.");
        }

        VerifyDataDevolucaoActual(rentRequestDTO);

        existingRent.setUser(foundUser);
        existingRent.setBook(foundBook);

        Rent updatedRent = rentRepository.save(existingRent);
        RentResponseDTO rentResponseDTO = rentMapper.toDTO(updatedRent);

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

    public Rent VerifyAndGetIfExists(Long rentId) {
        return rentRepository.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));
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

    private static void VerifyDataDevolucaoActual(RentRequestDTO rentRequestDTO) {
        LocalDate dataHoje = LocalDate.now();
        LocalDate dataDevolucao = rentRequestDTO.getData_devolucao();
        if (!dataDevolucao.isEqual(dataHoje)) {
            throw new DataDevolucaoActualInvalidException(rentRequestDTO);
        }
    }

    private static void verifyDataPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getData_aluguel();
        LocalDate dataPrevisao = rentRequestDTO.getData_previsao();

        if (dataPrevisao.isBefore(dataAluguel)) {
            throw new DataPrevisaoInvalidException("A data de previsão não pode ser menor que a data de aluguel.");
        }
    }
}
