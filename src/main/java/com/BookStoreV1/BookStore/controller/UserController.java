package com.BookStoreV1.BookStore.controller;

import com.BookStoreV1.BookStore.Service.UserService;
import com.BookStoreV1.BookStore.dto.MessageDTO;
import com.BookStoreV1.BookStore.dto.UserDTO;
import com.BookStoreV1.BookStore.repository.UserRepository;
import com.BookStoreV1.BookStore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Users")
public class UserController implements UserControllerDocs{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid UserDTO userToCreateDTo) {
        return userService.create(userToCreateDTo);
    }
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @GetMapping
    public List<UserDTO> findALL() {
        return userService.findALL();
    }
    @PutMapping("/{id}")
    public MessageDTO update(@PathVariable Long id, @RequestBody @Valid UserDTO userToUpdateDTo) {
        return userService.update(id, userToUpdateDTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


}
