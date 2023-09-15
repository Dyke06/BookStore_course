package com.BookStoreV1.BookStore.Publisher.mapper;

import com.BookStoreV1.BookStore.Publisher.dto.PublisherDTO;
import com.BookStoreV1.BookStore.Publisher.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);
}
