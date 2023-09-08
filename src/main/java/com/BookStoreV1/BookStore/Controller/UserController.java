package com.BookStoreV1.BookStore.Controller;

import com.BookStoreV1.BookStore.Service.UserService;
import com.BookStoreV1.BookStore.repository.UserRepository;
import com.BookStoreV1.BookStore.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/usuarios")
    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public User listarUsuariosUnico(@PathVariable(value = "id") long id){
        return userRepository.findById(id);
    }

    @PostMapping("/usuarios")
    public User salvarUsuario(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping("/usuarios")
    public void deletarUsuario(@RequestBody User user){
        userRepository.delete(user);
    }

    @PutMapping("/usuarios")
    public User atualizarUsuario(@RequestBody User user){
       return userRepository.save(user);
    }

}
