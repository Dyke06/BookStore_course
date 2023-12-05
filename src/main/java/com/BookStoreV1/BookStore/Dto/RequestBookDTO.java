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
    @Size(max = 35)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 35)
    private String author;

    @NotNull
    private Integer launch;

    @NotNull
    private Integer amount;

    @ApiModelProperty(hidden = true)
    private Integer totalRent;

    @NotNull
    private Long publisherId;
}
