package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.dto.UserReturnDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDto userDto) throws ParseException {
        this.passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findUser(userDto.getLogin(), userDto.getEmail());
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
        return userRepository.findAll().stream().map(UserReturnDto::new);
    }


    //TODO: Falta realizar os testes e otimização.
    public User refreshUser(RefreshUserDto refreshUser) {
//        Optional<User> oldUser = userRepository.findById(null);
//        if(oldUser.isPresent())
//        {
//            User user = refreshUser.user();
//            user.setId(oldUser.get().getId());
//            user.setLogin(oldUser.get().getLogin());
//            user.setCreatedAt(oldUser.get().getCreatedAt());
//            user.setBornDate(oldUser.get().getBornDate());
//            return userRepository.save(user);
//        }
        return null;
    }

    public List<User> isUserActive() {
        return userRepository.isActive();
    }
    public List<User> isUserInactive() {
        return userRepository.isInactive();
    }
}
