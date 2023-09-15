package com.BookStoreV1.BookStore.Users.mapper;

import com.BookStoreV1.BookStore.Users.dto.UserDTO;
import com.BookStoreV1.BookStore.Users.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toModel(UserDTO userDTO);
    UserDTO toDto(User user);
}
