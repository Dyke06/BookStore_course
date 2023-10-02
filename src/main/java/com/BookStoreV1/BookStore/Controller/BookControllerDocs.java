package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Books")
public interface BookControllerDocs {
    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success book creation"),
            @ApiResponse(code = 400, message = "Missing required, wrong field range value or boook already registred system")
    })
    BookResponseDTO create(BookRequestDTO bookRequestDTO);

    @ApiOperation(value = "Book find by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book found"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    BookResponseDTO findById(Long bookId);

    @ApiOperation(value = "List all books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List found informed")
    })

    List<BookResponseDTO> findAll();

    @ApiOperation(value = "Book delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success delete book"),
            @ApiResponse(code = 404, message = "Book not deleted error")
    })

    void delete( Long bookId);

    @ApiOperation(value = "Book update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success update book"),
            @ApiResponse(code = 404, message = "Book error update")
    })

    BookResponseDTO update(Long bookId, BookRequestDTO bookRequestDTO);
}
