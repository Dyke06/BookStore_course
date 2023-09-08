package com.BookStoreV1.BookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Native;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String endereco;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String email;
}
