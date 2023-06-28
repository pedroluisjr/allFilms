package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto) throws ParseException, JsonProcessingException {
        return ResponseEntity.status(201).body(userService.addUser(userDto));
    }

    @GetMapping("/allUsers")
    public Object allUsers() {
        return userService.listAll();
    }

    @PutMapping
    public User refreshUser(@RequestBody RefreshUserDto user) {
        return this.userService.refreshUser(user);
    }

    //TODO: Terminar implementação do JWT para autenticação.
    @GetMapping
    public List<User> allUsers(@RequestParam("active") boolean active) {
        if (active) {
            return userService.isUserActive();
        }
        return userService.isUserInactive();
    }
}
