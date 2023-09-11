package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api("Users managemen")
public interface UserControllerDocs {
    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success User creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or User already registered on system")
    })
    UserDTO create(UserDTO userDTO);
}
