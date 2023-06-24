package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public String saveUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
    @GetMapping("/allUsers")
    public Object allUsers () {
        return userService.listAll();
    }

}
