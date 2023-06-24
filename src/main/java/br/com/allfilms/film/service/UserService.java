package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserDto userDto) {
        Optional<User> user = userRepository.findUser(userDto.getLoginMail());
        if (user.isEmpty()) {
            userRepository.save(userDto.user());
            return "Usuário Cadastrado";
        } else {
            return "Login já existe";
        }
    }

    public Object listAll() {
        return userRepository.findAll();
    }
}
