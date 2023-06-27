package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
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

    @PutMapping("/{id}")
    public User refreshUser(@PathVariable("id") Long id, @RequestBody RefreshUserDto user) {
        return this.userService.refreshUser(id, user);
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
