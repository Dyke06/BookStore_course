package com.BookStoreV1.BookStore.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {

    private long id;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String cidade;

}
