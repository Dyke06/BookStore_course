package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.userException.UserAlreadExistsException;
import com.BookStoreV1.BookStore.dto.UserDTO;
import com.BookStoreV1.BookStore.mapper.UserMapper;
import com.BookStoreV1.BookStore.models.User;
import com.BookStoreV1.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO){
        verifyIfExists(userDTO.getEmail());
        User userToCreate = userMapper.toModel(userDTO);
        User createdUser = userRepository.save(userToCreate);
        return userMapper.toDto(createdUser);
    }

    private void verifyIfExists(String userEmail){
        userRepository.findByEmail(userEmail)
                .ifPresent(user -> {throw new UserAlreadExistsException(userEmail);});
    }
}
