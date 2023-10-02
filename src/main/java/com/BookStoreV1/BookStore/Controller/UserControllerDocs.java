package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.MessageDTO;
import com.BookStoreV1.BookStore.Dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;


@Api(tags = "Users")
public interface UserControllerDocs {
    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success User creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or User already registered on system")
    })
    MessageDTO create(UserDTO userToCreateDTo);
    @ApiOperation(value = "Find User by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success User found"),
            @ApiResponse(code = 404, message = "User not found error code")
    })

    UserDTO findById(Long id);

    @ApiOperation(value = "List all registred users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registred users"),
    })

    List<UserDTO> findALL();

    @ApiOperation(value = "Delete User by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success User Deleted"),
            @ApiResponse(code = 404, message = "User not found error code")
    })

    void delete(Long id);

    @ApiOperation(value = "User update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success User Update"),
            @ApiResponse(code = 404, message = "User not found error code")
    })

    MessageDTO update(Long id,UserDTO userToUpdateDTo);
}
