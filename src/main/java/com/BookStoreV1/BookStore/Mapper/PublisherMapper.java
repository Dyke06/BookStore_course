package com.BookStoreV1.BookStore.Mapper;

import com.BookStoreV1.BookStore.Dto.PublisherDTO;
import com.BookStoreV1.BookStore.Model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);
}
