package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Service.RentService;
import com.BookStoreV1.BookStore.Validation.Rent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("api/rent")
public class RentController implements RentControllerDocs {
    @Autowired
    private RentService rentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid RentRequestDTO rentRequestDTO) {
        try {
            RentResponseDTO responseDTO = rentService.create(rentRequestDTO);
            return ResponseEntity.ok(responseDTO);
        }catch (DataAluguelInvalidException e) {
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }catch (DataPrevisaoInvalidException e){
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Message: Data Devolução não pode ser definida agora.");
        }
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

    @PutMapping("/{rentId}")
    public ResponseEntity<?> update(@PathVariable Long rentId, @RequestBody @Valid RentRequestDTO rentRequestDTO) {
        try {
            RentResponseDTO responseDTO = rentService.update(rentRequestDTO, rentId);
            return ResponseEntity.ok(responseDTO);
        } catch (ReturnDateCannotBeNull e){
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }catch (DataDevolucaoActualInvalidException e){
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }
    }


}
