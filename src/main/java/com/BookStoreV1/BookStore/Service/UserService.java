package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.userException.UserAlreadExistsException;
import com.BookStoreV1.BookStore.dto.UserDTO;
import com.BookStoreV1.BookStore.mapper.UserMapper;
import com.BookStoreV1.BookStore.models.User;
import com.BookStoreV1.BookStore.repository.UserRepository;
import com.BookStoreV1.BookStore.userException.userNotFoundException;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO userDTO){
        verifyIfExists(userDTO.getEmail());
        User userToCreate = userMapper.toModel(userDTO);
        User createdUser = userRepository.save(userToCreate);
        return userMapper.toDto(createdUser);
    }

    public UserDTO findById(Long id){
        User foundUser = verifyAndGetUser(id);
        return userMapper.toDto(foundUser);
    }

    public List<UserDTO> findALL(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        verifyAndGetUser(id);
        userRepository.deleteById(id);
    }

    private User verifyAndGetUser(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new userNotFoundException(id));
        return foundUser;

    }
    private void verifyIfExists(String userEmail){
        userRepository.findByEmail(userEmail)
                .ifPresent(user -> {throw new UserAlreadExistsException(userEmail);});
    }
}
