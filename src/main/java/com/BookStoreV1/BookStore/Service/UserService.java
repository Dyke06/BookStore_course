package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Validation.User.UserAlreadExistsException;
import com.BookStoreV1.BookStore.Dto.MessageDTO;
import com.BookStoreV1.BookStore.Dto.UserDTO;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.UserRepository;
import com.BookStoreV1.BookStore.Mapper.UserMapper;
import com.BookStoreV1.BookStore.Validation.User.UserNotFoundException;
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

    public User verifyAndGetUser(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return foundUser;

    }
    private void verifyIfExists(String userEmail){
        userRepository.findByEmail(userEmail)
                .ifPresent(user -> {throw new UserAlreadExistsException(userEmail);});
    }
}
