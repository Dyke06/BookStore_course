package com.BookStoreV1.BookStore.Validation.Rent.impl;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Rent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class RentValidatoImpl {

    public void validateRentRequest(RentRequestDTO rentRequestDTO, Book foundBook) {
        verifyDateRent(rentRequestDTO);
        verifyDataDevolucaoIsNull(rentRequestDTO.getDateReturn());
        verifyDataPrevisao(rentRequestDTO);
        verifyDataMonthPrevisao(rentRequestDTO);
        VerifyStock(rentRequestDTO, foundBook);
        verifyUserAndBook(rentRequestDTO);
    }

    private final RentRepository rentRepository;

    public RentValidatoImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public void verifyDataDevolucaoIsNull(LocalDate dataDevolucao) {
        if (dataDevolucao != null) {
            throw new DataDevolucaoIsNull(dataDevolucao);
        }
    }

    public static void verifyDataMonthPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getDateRent();
        LocalDate dataPrevisao = rentRequestDTO.getDateForecast();
        LocalDate umMesDepois = dataAluguel.plusMonths(1);

        if (dataPrevisao.isAfter(umMesDepois)){
            throw new DataPrevisaoMonthException(rentRequestDTO);
        }
    }

    public static void VerifyStock(RentRequestDTO rentRequestDTO, Book foundBook) {
        int quantidadeLivro = foundBook.getAmount();

        if (quantidadeLivro == 0) {
            throw new EstoqueEsgotadoException(rentRequestDTO);
        }
    }

    public static void verifyDateRent(RentRequestDTO rentRequestDTO) {
        if (rentRequestDTO.getDateRent() == null) {
            rentRequestDTO.setDateRent(LocalDate.now());
        }
    }

    public void verifyUserAndBook(RentRequestDTO rentRequestDTO) {
        // Verifique se existe um aluguel com o status "Pendente" ou com data de devolução nula
        if (rentRepository.existsByUserIdAndBookIdAndStatus(
                rentRequestDTO.getUserId(), rentRequestDTO.getBookId(), "Pendente")) {
            throw new RentUserBook();
        }
    }

    public static void verifyDataPrevisao(RentRequestDTO rentRequestDTO) {
        LocalDate dataAluguel = rentRequestDTO.getDateRent();
        LocalDate dataPrevisao = rentRequestDTO.getDateForecast();

        if (dataPrevisao.isBefore(dataAluguel)) {
            throw new DataPrevisaoInvalidException(rentRequestDTO);
        }
    }

}
