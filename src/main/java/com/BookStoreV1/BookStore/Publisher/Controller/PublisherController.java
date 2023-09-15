package com.BookStoreV1.BookStore.Publisher.Controller;

import com.BookStoreV1.BookStore.Publisher.Service.PublisherService;
import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import com.BookStoreV1.BookStore.Publisher.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/publisher")
public class PublisherController implements PublisherControllerDocs{
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private PublisherRepository publisherRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDTO create(@RequestBody @Valid PublisherDTO publisherDTO) {
        return publisherService.create(publisherDTO);
    }
    @GetMapping
    public List<PublisherDTO> findAll() {
        return publisherService.findAll();
    }

    @GetMapping("/{id}")
    public PublisherDTO findById(@PathVariable Long id) {
        return publisherService.findById(id);
    }
    @PutMapping("/{id}")
    public PublisherDTO update(@PathVariable Long id,@RequestBody @Valid PublisherDTO updatedPublisherDTO) {
        return publisherService.update(id, updatedPublisherDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        publisherService.delete(id);
    }
}
