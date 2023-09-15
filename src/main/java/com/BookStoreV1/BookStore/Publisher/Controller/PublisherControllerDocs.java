package com.BookStoreV1.BookStore.Publisher.Controller;

import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Publisher manggement")
public interface PublisherControllerDocs {

    @ApiOperation(value = "Publisher cration operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publishe creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or use already registed.")
    })

    PublisherDTO create(PublisherDTO publisherDTO);
}
