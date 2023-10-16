package com.BookStoreV1.BookStore.Mapper;

import com.BookStoreV1.BookStore.Dto.UserDTO;
import com.BookStoreV1.BookStore.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO);
    UserDTO toDto(User user);
}
