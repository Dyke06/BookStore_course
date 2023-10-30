package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.PublisherDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Publishers-controller")
public interface PublisherControllerDocs {

    @ApiOperation(value = "Publisher cration operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publishe creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or use already registed.")
    })
    PublisherDTO create(PublisherDTO publisherDTO);

    @ApiOperation(value = "Find publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publishe found"),
            @ApiResponse(code = 404, message = "Publisher not found errors.")
    })
    PublisherDTO findById( Long id);
    @ApiOperation(value = "List all registred publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return all publishers"),
    })
    List<PublisherDTO> findAll();
    @ApiOperation(value = "Delete publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success publishe delete"),
            @ApiResponse(code = 404, message = "Publisher not found errors.")
    })
    void delete(Long id);

    @ApiOperation(value = "Publisher update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success Publisher Update"),
            @ApiResponse(code = 404, message = "Publisher not found error code")
    })

    PublisherDTO update(Long id,PublisherDTO updatedPublisherDTO);


}
