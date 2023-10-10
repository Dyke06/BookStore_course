package com.BookStoreV1.BookStore.Mapper;

import com.BookStoreV1.BookStore.Dto.RentRequestDTO;
import com.BookStoreV1.BookStore.Dto.RentResponseDTO;
import com.BookStoreV1.BookStore.Dto.RentUpdateDTO;
import com.BookStoreV1.BookStore.Model.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentMapper {
    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);
    @Mapping(target = "id", ignore = true)
    Rent toModel(RentRequestDTO rentRequestDTO);

    Rent toModel(RentResponseDTO rentResponseDTO);

    Rent toModel(RentUpdateDTO rentUpdateDTO);

    RentResponseDTO toDTO(Rent rent);
}

