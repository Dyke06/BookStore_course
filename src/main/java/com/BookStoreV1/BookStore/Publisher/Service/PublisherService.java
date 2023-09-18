package com.BookStoreV1.BookStore.Publisher.Service;

import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import com.BookStoreV1.BookStore.Publisher.exception.PublisherAlreadyExistsException;
import com.BookStoreV1.BookStore.Publisher.exception.PublisherNotFoundException;
import com.BookStoreV1.BookStore.Publisher.mapper.PublisherMapper;
import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import com.BookStoreV1.BookStore.Publisher.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

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
        Publisher existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        if (updatedPublisherDTO.getNome() != null) {
            existingPublisher.setNome(updatedPublisherDTO.getNome());
            existingPublisher.setCidade(updatedPublisherDTO.getCidade());
        }
        Publisher updatedPublisher = publisherRepository.save(existingPublisher);
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
