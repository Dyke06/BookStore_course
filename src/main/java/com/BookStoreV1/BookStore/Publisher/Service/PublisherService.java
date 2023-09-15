package com.BookStoreV1.BookStore.Publisher.Service;

import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import com.BookStoreV1.BookStore.Publisher.exception.PublisherAlreadyExistsException;
import com.BookStoreV1.BookStore.Publisher.mapper.PublisherMapper;
import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import com.BookStoreV1.BookStore.Publisher.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {
    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
    @Autowired
    private PublisherRepository publisherRepository;

    public PublisherDTO create(PublisherDTO publisherDTO){
        verifyExists(publisherDTO.getNome());
        Publisher publisherToCreate = publisherMapper.toModel(publisherDTO);
        Publisher createdPublisher = publisherRepository.save(publisherToCreate);
        return publisherMapper.toDTO(createdPublisher);
    }

    private void verifyExists(String name) {
        Optional<Publisher> duplicatedPublisher = publisherRepository
                .findByNome(name);
        if (duplicatedPublisher.isPresent()){
            throw new PublisherAlreadyExistsException(name);
        }
    }

}
