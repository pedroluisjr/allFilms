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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
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

    public String addUser(@Valid UserDto userDto) throws ParseException {
        Optional<User> user = userRepository.findUser(userDto.getLogin(), userDto.getEmail());
        if (user.isEmpty()) {
            userDto.setPassword(passwordEncode(userDto.getPassword()));
            userRepository.save(userDto.user());
            return "Usuário Cadastrado";
        } else {
            return null;
        }
    }

    public Object listAll() {
        return userRepository.findAll().stream().map(UserReturnDto::new);
    }


    //TODO: Não está passando pelo constructor no return.
    public UserReturnDto refreshUser(RefreshUserDto refreshUser, Long id) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent())
        {
            User user = refreshUser.userAtt(oldUser.get());
            user.setPassword(passwordEncode(refreshUser.getPassword()));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.convertValue(userRepository.save(user), UserReturnDto.class);
        }
        return null;
    }

    public List<User> isUserActive() {
        return userRepository.isActive();
    }
    public List<User> isUserInactive() {
        return userRepository.isInactive();
    }
}
