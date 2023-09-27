package com.BookStoreV1.BookStore.Mapper;

import com.BookStoreV1.BookStore.Dto.BookRequestDTO;
import com.BookStoreV1.BookStore.Dto.BookResponseDTO;
import com.BookStoreV1.BookStore.Model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toModel(BookRequestDTO bookRequestDTO);

    Book toModel(BookResponseDTO bookResponseDTO);

    BookResponseDTO toDTO(Book book);

}
