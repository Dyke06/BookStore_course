package com.BookStoreV1.BookStore.User.service;

import com.BookStoreV1.BookStore.Service.UserService;
import com.BookStoreV1.BookStore.User.builder.UserDTOBuilder;
import com.BookStoreV1.BookStore.Validation.UserAlreadExistsException;
import com.BookStoreV1.BookStore.Dto.UserDTO;
import com.BookStoreV1.BookStore.Mapper.UserMapper;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDTOBuilder userDTOBuilder;

    @BeforeEach
    void setUp() {
        userDTOBuilder = UserDTOBuilder.builder().build();
    }

    @Test
    void whenNewUserIsInformedThenItShouldBeCreated() {
        //given
       UserDTO expectedUserToCreateDTO = userDTOBuilder.builderUserDTO();
       User expectedCreatedUser = userMapper.toModel(expectedUserToCreateDTO);

       //when
        when(userRepository.save(expectedCreatedUser)).thenReturn(expectedCreatedUser);
        when(userRepository.findByEmail(expectedUserToCreateDTO.getEmail())).thenReturn(Optional.empty());


        //then


    }

    @Test
    void whenExistingUserIsInformedThenAnExceptionShouldBeThrown() {

        UserDTO expectedUserToCreateDTO = userDTOBuilder.builderUserDTO();
        User expectedCreatedUser = userMapper.toModel(expectedUserToCreateDTO);

        when(userRepository.findByEmail(expectedUserToCreateDTO.getEmail()))
                .thenReturn(Optional.of(expectedCreatedUser));

        assertThrows(UserAlreadExistsException.class, () -> userService.create(expectedUserToCreateDTO));

    }
}
