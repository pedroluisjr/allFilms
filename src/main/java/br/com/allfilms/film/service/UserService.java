package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDto userDto) throws ParseException, JsonProcessingException {
        this.passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findUser(userDto.getLogin(), userDto.getEmail());
        Date bornDate = new SimpleDateFormat("dd/MM/yyyy").get2DigitYearStart();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        String formattedDate = objectMapper.writeValueAsString(bornDate);
        userDto.setBornDate(formattedDate);
        if (user.isEmpty()) {
            String encode = this.passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encode);
            userRepository.save(userDto.user());
            return "Usuário Cadastrado";
        } else {
            return "Login já existe";
        }
    }

    public Object listAll() {
        return userRepository.findAll();
    }

    public User refreshUser(Long id, RefreshUserDto refreshUser) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent())
        {
            //TODO: Falta realizar os testes e otimização.
            User user = refreshUser.user();
            user.setId(oldUser.get().getId());
            user.setLogin(oldUser.get().getLogin());
            user.setCreatedAt(oldUser.get().getCreatedAt());
            user.setBornDate(oldUser.get().getBornDate());
            return userRepository.save(user);
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
