package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Rents module manage")
public interface RentControllerDocs {

    @ApiOperation(value = "Rent Operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucess creation"),
            @ApiResponse(code = 400, message = "Error creation rent")
    })
    RentResponseDTO create(RentRequestDTO rentRequestDTO);
}
