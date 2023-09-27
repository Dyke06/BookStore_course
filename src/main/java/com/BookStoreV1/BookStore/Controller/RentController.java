package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/rent")
public class RentController implements RentControllerDocs {
    @Autowired
    private RentService rentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentResponseDTO create(@RequestBody @Valid RentRequestDTO rentRequestDTO) {
        return rentService.create(rentRequestDTO);
    }
}
