package com.BookStoreV1.BookStore.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBookDTO {
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String autor;

    @NotNull
    private LocalDate lancamento;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    private Long publisherId;
}
