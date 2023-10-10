package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.PublisherDTO;
import com.BookStoreV1.BookStore.Validation.Publisher.PublisherAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Publisher.PublisherNotFoundException;
import com.BookStoreV1.BookStore.Mapper.PublisherMapper;
import com.BookStoreV1.BookStore.Model.Publisher;
import com.BookStoreV1.BookStore.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PublisherDTO findById(Long id){
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public List<PublisherDTO> findAll(){
        return publisherRepository.findAll()
                .stream()
                .map(publisherMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        verifyGetIfExists(id);
        publisherRepository.deleteById(id);
    }

    public PublisherDTO update(Long id, PublisherDTO updatedPublisherDTO) {
        Publisher foundPublisher = verifyGetIfExists(id);
        updatedPublisherDTO.setId(foundPublisher.getId());
        Publisher publisherUpdate = publisherMapper.toModel(updatedPublisherDTO);
        Publisher updatedPublisher = publisherRepository.save(publisherUpdate);
        return publisherMapper.toDTO(updatedPublisher);
    }

    private void verifyExists(String name) {
        Optional<Publisher> duplicatedPublisher = publisherRepository
                .findByNome(name);
        if (duplicatedPublisher.isPresent()){
            throw new PublisherAlreadyExistsException(name);
        }
    }

    public Publisher verifyGetIfExists(Long id) {
       return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

}
