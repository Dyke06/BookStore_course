package com.BookStoreV1.BookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentResponseDTO {

    private long id;

    private LocalDate dateRent;

    private LocalDate dateForecast;

    private LocalDate dateReturn;

    private UserDTO user;

    private BookResponseDTO book;

}
