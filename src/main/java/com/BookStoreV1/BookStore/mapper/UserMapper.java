package com.BookStoreV1.BookStore.mapper;

import com.BookStoreV1.BookStore.dto.UserDTO;
import com.BookStoreV1.BookStore.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO);
    UserDTO toDto(User user);
}
