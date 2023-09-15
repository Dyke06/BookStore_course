package com.BookStoreV1.BookStore.Users.Service;

import com.BookStoreV1.BookStore.Users.userException.UserAlreadExistsException;
import com.BookStoreV1.BookStore.Users.dto.MessageDTO;
import com.BookStoreV1.BookStore.Users.dto.UserDTO;
import com.BookStoreV1.BookStore.Users.models.User;
import com.BookStoreV1.BookStore.Users.repository.UserRepository;
import com.BookStoreV1.BookStore.Users.mapper.UserMapper;
import com.BookStoreV1.BookStore.Users.userException.userNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    public MessageDTO create(UserDTO userToCreateDTo){
        VerifyExistss(userToCreateDTo.getEmail());
        User userToCreate = userMapper.toModel(userToCreateDTo);
       User createdUser = userRepository.save(userToCreate);
       return creationMessage(createdUser);
    }

    public MessageDTO update(Long id, UserDTO userToUpdateDTo){
        User foundUser = verifyAndGetUser(id);

        userToUpdateDTo.setId(foundUser.getId());
        User userToUpdate = userMapper.toModel(userToUpdateDTo);
        User updateUser = userRepository.save(userToUpdate);
        return updateMessage(updateUser);
    }

    private void VerifyExistss(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()){
            throw new UserAlreadExistsException(email);
        }
    }

    private MessageDTO creationMessage(User createdUser) {
        return returnMessage(createdUser, "Createdd");
    }

    private MessageDTO updateMessage(User updateUser) {
        return returnMessage(updateUser, "Updatedd");
    }

    private MessageDTO returnMessage(User updateUser, String action) {
        String username = updateUser.getNome();
        Long createdId = updateUser.getId();
        String createdUserMessage = String.format("UserName %s with ID %s successfuly %s", username, createdId, action);
        return MessageDTO.builder()
                .message(createdUserMessage)
                .build();
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
