package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Dto.RentUpdateDTO;
import com.BookStoreV1.BookStore.Mapper.RentMapper;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Rent.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RentService {

    private final RentMapper rentMapper = RentMapper.INSTANCE;

    private final RentRepository rentRepository;

    private BookService bookService;

    private UserService userService;

    private RentUpdateDTO rentUpdateDTO;


    public RentResponseDTO create(RentRequestDTO rentRequestDTO) throws EstoqueEsgotadoException {
        if (rentRequestDTO.getDateRent() == null) {
            rentRequestDTO.setDateRent(LocalDate.now());
        }
        if (rentRepository.existsByUserIdAndBookId(rentRequestDTO.getUserId(), rentRequestDTO.getBookId())) {
            throw new RentUserBook();
        }
        User foundUser = userService.verifyAndGetUser(rentRequestDTO.getUserId());
        Book foundBook = bookService.verifyAndGetIfExists(rentRequestDTO.getBookId());

        verifyDataDevolucaoIsNull(rentRequestDTO.getDateReturn());
        verifyDataPrevisao(rentRequestDTO);
        verifyDataMonthPrevisao(rentRequestDTO);
        VerifyStock(rentRequestDTO, foundBook);

        Rent rentToSave = rentMapper.toModel(rentRequestDTO);
        rentToSave.setUser(foundUser);
        rentToSave.setBook(foundBook);
        rentToSave.setStatus("Pendente");

        Rent savedRent = rentRepository.save(rentToSave);
        foundBook.setAmount(foundBook.getAmount() - 1);
        bookService.update(foundBook.getId(), foundBook);
        foundBook.setTotalRent(foundBook.getTotalRent() + 1);
        bookService.update(foundBook.getId(), foundBook);

        RentResponseDTO rentResponseDTO = rentMapper.toDTO(savedRent);

        return rentResponseDTO;
    }

    public RentResponseDTO update(Long id) {
        Rent existingRent = VerifyAndGetIfExists(id);

        RentReturned(existingRent);
        VerifyIfDateReturnNULL(existingRent);
        VerifyStatus(existingRent);

        Rent updatedRent = rentRepository.save(existingRent);
        Book foundBook = existingRent.getBook();
        foundBook.setAmount(foundBook.getAmount() + 1);
        bookService.update(foundBook.getId(), foundBook);

        RentResponseDTO rentResponseDTO = rentMapper.toDTO(updatedRent);

        return rentResponseDTO;
    }


    private static void RentReturned(Rent existingRent) {
        if (existingRent.getDateReturn() != null) {
            throw new RentReturnedException(existingRent);
        }
    }

    public List<RentResponseDTO> findALL(){
        return rentRepository.findAll()
                .stream()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RentResponseDTO findById(Long id){
        return rentRepository.findById(id)
                .map(rentMapper::toDTO)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public long countPendentes() {
        return rentRepository.countPendentes();
    }

    public long countNoPrazo() {
        return rentRepository.countNoPrazo();
    }

    public long countAtrasado() {
        return rentRepository.countAtrasado();
    }

    public Rent VerifyAndGetIfExists(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public void verifyDataDevolucaoIsNull(LocalDate dataDevolucao) {
        if (dataDevolucao != null) {
            throw new DataDevolucaoIsNull(dataDevolucao);
        }
    }

    private static void VerifyStatus(Rent existingRent) {
        if (existingRent.getDateReturn().isAfter(existingRent.getDateForecast())) {
            existingRent.setStatus("Atrasado");
        } else {
            existingRent.setStatus("No prazo");
        }
    }

    private static void verifyDataPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getDateRent();
        LocalDate dataPrevisao = rentRequestDTO.getDateForecast();

        if (dataPrevisao.isBefore(dataAluguel)) {
            throw new DataPrevisaoInvalidException(rentRequestDTO);
        }
    }

    private static void verifyDataMonthPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getDateRent();
        LocalDate dataPrevisao = rentRequestDTO.getDateForecast();
        LocalDate umMesDepois = dataAluguel.plusMonths(1);

        if (dataPrevisao.isAfter(umMesDepois)){
            throw new DataPrevisaoMonthException(rentRequestDTO);
        }
    }

    private static void VerifyStock(RentRequestDTO rentRequestDTO, Book foundBook) {
        int quantidadeLivro = foundBook.getAmount();

        if (quantidadeLivro == 0) {
            throw new EstoqueEsgotadoException(rentRequestDTO);
        }
    }

    private void VerifyIfDateReturnNULL(Rent existingRent) {
        if (rentUpdateDTO.getDateReturn() == null) {
            existingRent.setDateReturn(LocalDate.now());
        } else {
            existingRent.setDateReturn(rentUpdateDTO.getDateReturn());
        }
    }

}
