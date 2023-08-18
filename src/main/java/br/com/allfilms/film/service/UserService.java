package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.dto.UserReturnDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private String passwordEncode (String password) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        return this.passwordEncoder.encode(password);
    }

    public ResponseEntity<User> addUser(@Valid UserDto userDto) {
        Optional<User> user = userRepository.findUser(userDto.getLogin(), userDto.getEmail());
        if (user.isEmpty()) {
            userDto.setPassword(passwordEncode(userDto.getPassword()));
            userDto.setActive(true);
            userRepository.save(userDto.toUser());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public Object listAll() {
        return userRepository.findAll().stream().map(UserReturnDto::new);
    }


    //TODO: Não está passando pelo constructor no return.
    public ResponseEntity<UserReturnDto> refreshUser(RefreshUserDto refreshUser, Long id) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent())
        {
            User user = refreshUser.userAtt(oldUser.get());
            user.setPassword(passwordEncode(refreshUser.getPassword()));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.convertValue(userRepository.save(user), UserReturnDto.class);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public List<User> isUserActive() {
        return userRepository.isActive();
    }
    public List<User> isUserInactive() {
        return userRepository.isInactive();
    }

    //TODO: REALIZAR AS IMPLEMENTAÇÕES PARA HISTORICO.

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
