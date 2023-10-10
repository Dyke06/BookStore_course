package com.BookStoreV1.BookStore.Service;

import com.BookStoreV1.BookStore.Validation.User.RelatedUserRent;
import com.BookStoreV1.BookStore.Validation.User.UserAlreadExistsException;
import com.BookStoreV1.BookStore.Dto.UserDTO;
import com.BookStoreV1.BookStore.Model.User;
import com.BookStoreV1.BookStore.Repository.UserRepository;
import com.BookStoreV1.BookStore.Mapper.UserMapper;
import com.BookStoreV1.BookStore.Validation.User.UserNotFoundException;
import lombok.SneakyThrows;
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
    @Lazy
    private RentService rentService;

    public UserDTO create(UserDTO userDTO){
        VerifyExistss(userDTO.getEmail());
        User userToCreate = userMapper.toModel(userDTO);
        User createdUser = userRepository.save(userToCreate);
        return userMapper.toDto(createdUser);
    }

    public UserDTO update(Long id, UserDTO userDTO){
        User foundUser = verifyAndGetUser(id);

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
        verifyAndGetUser(id);
        if (rentService.hasRentsForUser(id)) {
            throw new RelatedUserRent(id);
        }
        userRepository.deleteById(id);
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
