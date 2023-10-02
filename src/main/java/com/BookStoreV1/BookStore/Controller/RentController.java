package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rent")
public class RentController implements RentControllerDocs {
    @Autowired
    private RentService rentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createRent(@RequestBody @Valid RentRequestDTO rentRequestDTO) {
        try {
            RentResponseDTO responseDTO = rentService.create(rentRequestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body("Não pode ter valor");
        }
    }

    @Override
    public RentResponseDTO create(RentRequestDTO rentRequestDTO) {
        return null;
    }

    @GetMapping
    public List<RentResponseDTO> findALL() {
        return rentService.findALL();
    }
    @GetMapping("/{rentId}")
    public RentResponseDTO findById(@PathVariable Long rentId) {
        return rentService.findById(rentId);
    }
    @DeleteMapping("/{rentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long rentId) {
        rentService.delete(rentId);
    }
}
