package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.dto.UserReturnDto;
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
@RequestMapping("/api/usuario")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDto userDto) throws ParseException {
        return userService.addUser(userDto);
    }

    @GetMapping
    public Object allUsers() {
        return userService.listAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReturnDto> refreshUser(@PathVariable("id") Long id, @RequestBody RefreshUserDto user) {
        return this.userService.refreshUser(user, id);
    }

    //TODO: Terminar implementação do JWT para autenticação.
    @GetMapping("/ativos")
    public List<User> allUsers(@RequestParam("active") boolean active) {
        if (active) {
            return userService.isUserActive();
        }
        return userService.isUserInactive();
    }
}
