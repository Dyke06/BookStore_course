package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Dto.PublisherDTO;
import com.BookStoreV1.BookStore.Model.Book;
import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Repository.BookRepository;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.Publisher.PublisherAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Publisher.PublisherNameAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.Publisher.PublisherNotFoundException;
import com.BookStoreV1.BookStore.Mapper.PublisherMapper;
import com.BookStoreV1.BookStore.Model.Publisher;
import com.BookStoreV1.BookStore.Repository.PublisherRepository;
import com.BookStoreV1.BookStore.Validation.Publisher.RelatedPublisherRent;
import com.BookStoreV1.BookStore.Validation.User.EmailAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.User.RelatedUserRent;
import lombok.SneakyThrows;
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
    @Autowired
    private BookRepository bookRepository;

    public PublisherDTO create(PublisherDTO publisherDTO){
        verifyExists(publisherDTO.getName());
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

    @SneakyThrows
    public void delete(Long id){
        Publisher foundPublisherDelete = verifyGetIfExists(id);
        if (hasRentsForPublisher(id)) {
            throw new RelatedPublisherRent(foundPublisherDelete.getId());
        }
        publisherRepository.deleteById(id);
    }

    public boolean hasRentsForPublisher (Long id) {
        List<Book> books = bookRepository.findByPublisherId(id);
        return !books.isEmpty();
    }

    public PublisherDTO update(Long id, PublisherDTO updatedPublisherDTO) {
        Publisher foundPublisher = verifyGetIfExists(id);
        if (publisherRepository.existsByNameAndIdNot(updatedPublisherDTO.getName(), id)) {
            throw new PublisherNameAlreadyExistsException();
        }
        updatedPublisherDTO.setId(foundPublisher.getId());
        Publisher publisherUpdate = publisherMapper.toModel(updatedPublisherDTO);
        Publisher updatedPublisher = publisherRepository.save(publisherUpdate);
        return publisherMapper.toDTO(updatedPublisher);
    }

    private void verifyExists(String name) {
        Optional<Publisher> duplicatedPublisher = publisherRepository
                .findByName(name);
        if (duplicatedPublisher.isPresent()){
            throw new PublisherAlreadyExistsException(name);
        }
    }

    public Publisher verifyGetIfExists(Long id) {
       return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

}
