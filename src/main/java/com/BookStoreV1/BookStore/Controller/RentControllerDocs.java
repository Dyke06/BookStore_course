package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Rents")
public interface RentControllerDocs {

    @ApiOperation(value = "Rent Operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucess creation"),
            @ApiResponse(code = 400, message = "Error creation rent")
    })
    ResponseEntity<?> create(RentRequestDTO rentRequestDTO);

    @ApiOperation(value = "List all rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List found informed"),
    })

    List<RentResponseDTO> findALL();

    @ApiOperation(value = "Rent find by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success rent found"),
            @ApiResponse(code = 404, message = "rent not found error")
    })

    RentResponseDTO findById(Long rentId);

    @ApiOperation(value = "Rent delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success rent book"),
            @ApiResponse(code = 404, message = "Book not rent error")
    })
    void delete(Long rentId);
}
