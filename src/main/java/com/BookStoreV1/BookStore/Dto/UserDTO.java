package com.BookStoreV1.BookStore.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Native;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @ApiModelProperty(hidden = true)
    private long id;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String address;

    @NotNull
    @NotEmpty(message = "A cidade não pode ser vazio.")
    @Size(max = 35)
    private String city;

    @NotNull
    @Email(message = "Informe um email valido.")
    @NotEmpty
    @Size(max = 35)
    private String email;
}
