package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Model.Rent;
import com.BookStoreV1.BookStore.Repository.RentRepository;
import com.BookStoreV1.BookStore.Validation.User.EmailAlreadyExistsException;
import com.BookStoreV1.BookStore.Validation.User.RelatedUserRent;
import com.BookStoreV1.BookStore.Validation.User.UserAlreadExistsException;
import com.BookStoreV1.BookStore.Dto.UserDTO;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.UserRepository;
import com.BookStoreV1.BookStore.Mapper.UserMapper;
import com.BookStoreV1.BookStore.Validation.User.UserNotFoundException;
import lombok.SneakyThrows;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentRepository rentRepository;


    public UserDTO create(UserDTO userDTO){
        VerifyExistss(userDTO.getEmail());
        User userToCreate = userMapper.toModel(userDTO);
        User createdUser = userRepository.save(userToCreate);
        return userMapper.toDto(createdUser);
    }

    @SneakyThrows
    public UserDTO update(Long id, UserDTO userDTO){
        User foundUser = verifyAndGetUser(id);
        if (userRepository.existsByEmailAndIdNot(userDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException();
        }

        foundUser.setName(userDTO.getName());
        foundUser.setEmail(userDTO.getEmail());
        userDTO.setId(foundUser.getId());
        User userToUpdate = userMapper.toModel(userDTO);
        User updateUser = userRepository.save(userToUpdate);
        return userMapper.toDto(updateUser);
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

    @SneakyThrows
    public void delete(Long id){
        User foundUserDelete = verifyAndGetUser(id);
        if (hasRentsForUser(id)) {
            throw new RelatedUserRent(foundUserDelete.getId());
        }
        userRepository.deleteById(id);
    }

    public boolean hasRentsForUser (Long id) {
        List<Rent> rents = rentRepository.findByUserId(id);
        return !rents.isEmpty();
    }

    private void VerifyExistss(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()){
            throw new UserAlreadExistsException(email);
        }
    }


    public User verifyAndGetUser(Long id){
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return foundUser;

    }
}
