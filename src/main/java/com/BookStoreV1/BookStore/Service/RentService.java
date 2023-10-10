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
    @Lazy
    private final RentRepository rentRepository;

    private BookService bookService;

    @Lazy
    private UserService userService;

    public RentResponseDTO create(RentRequestDTO rentRequestDTO) throws EstoqueEsgotadoException {
        User foundUser = userService.verifyAndGetUser(rentRequestDTO.getUserId());
        Book foundBook = bookService.verifyAndGetIfExists(rentRequestDTO.getBookId());

        verifyDataDevolucaoIsNull(rentRequestDTO.getData_devolucao());
        VerifyDataAluguel(rentRequestDTO);
        verifyDataPrevisao(rentRequestDTO);
        verifyDataMonthPrevisao(rentRequestDTO);
        VerifyStock(rentRequestDTO, foundBook);

        Rent rentToSave = rentMapper.toModel(rentRequestDTO);
        rentToSave.setUser(foundUser);
        rentToSave.setBook(foundBook);

        Rent savedRent = rentRepository.save(rentToSave);

        foundBook.setQuantidade(foundBook.getQuantidade() - 1);
        bookService.update(foundBook.getId(), foundBook);

        foundBook.setTotalalugado(foundBook.getTotalalugado() + 1);
        bookService.update(foundBook.getId(), foundBook);

        RentResponseDTO rentResponseDTO = rentMapper.toDTO(savedRent);

        return rentResponseDTO;
    }

    public RentResponseDTO update(RentUpdateDTO rentUpdateDTO, Long id) {
        Rent existingRent = VerifyAndGetIfExists(id);

        DataDevolucaoIsNotNull(rentUpdateDTO, existingRent);
        VerifyDataDevolucaoActual(rentUpdateDTO);

        Rent updatedRent = rentRepository.save(existingRent);
        Book foundBook = existingRent.getBook();

        foundBook.setQuantidade(foundBook.getQuantidade() + 1);
        bookService.update(foundBook.getId(), foundBook);

        RentResponseDTO rentResponseDTO = rentMapper.toDTO(updatedRent);

        return rentResponseDTO;
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

    public void delete(Long id){
        Rent FoundRent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rentRepository.deleteById(FoundRent.getId());
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

    private static void VerifyDataAluguel(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguelHoje = LocalDate.now();
        LocalDate dataAluguel = rentRequestDTO.getData_aluguel();
        if (!dataAluguel.isEqual(dataAluguelHoje)) {
            throw new DataAluguelInvalidException(rentRequestDTO);
        }
    }

    private static void VerifyDataDevolucaoActual(RentUpdateDTO rentUpdateDTO) {
        LocalDate dataHoje = LocalDate.now();
        LocalDate dataDevolucao = rentUpdateDTO.getData_devolucao();
        if (!dataDevolucao.isEqual(dataHoje)) {
            throw new DataDevolucaoActualInvalidException(rentUpdateDTO);
        }
    }

    private static void DataDevolucaoIsNotNull(RentUpdateDTO rentUpdateDTO, Rent existingRent) {
        LocalDate dataDevolucao = rentUpdateDTO.getData_devolucao();
        if (dataDevolucao != null) {
            existingRent.setData_devolucao(dataDevolucao);
        } else {
            throw new ReturnDateCannotBeNull(rentUpdateDTO);
        }
    }

    private static void verifyDataPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getData_aluguel();
        LocalDate dataPrevisao = rentRequestDTO.getData_previsao();

        if (dataPrevisao.isBefore(dataAluguel)) {
            throw new DataPrevisaoInvalidException(rentRequestDTO);
        }
    }

    private static void verifyDataMonthPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getData_aluguel();
        LocalDate dataPrevisao = rentRequestDTO.getData_previsao();

        // Calcule a data 1 mês após a data de aluguel
        LocalDate umMesDepois = dataAluguel.plusMonths(1);

        if (dataPrevisao.isAfter(umMesDepois)){
            throw new DataPrevisaoMonthException(rentRequestDTO);
        }
    }

    private static void VerifyStock(RentRequestDTO rentRequestDTO, Book foundBook) {
        int quantidadeLivro = foundBook.getQuantidade();

        if (quantidadeLivro == 0) {
            throw new EstoqueEsgotadoException(rentRequestDTO);
        }
    }

    public boolean hasRentsForUser(Long userId) {
        List<Rent> rents = rentRepository.findByUserId(userId);
        return !rents.isEmpty();
    }

}
