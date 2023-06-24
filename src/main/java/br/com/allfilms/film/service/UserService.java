package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.RefreshUserDto;
import br.com.allfilms.film.dto.UserDto;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Id;
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
        Optional<User> user = userRepository.findUser(userDto.getLoginMail());
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
            //TODO: Falta realizar os testes, e otimização.
            User user = refreshUser.user();
            user.setPersonId(oldUser.get().getPersonId());
            user.setLoginMail(oldUser.get().getLoginMail());
            user.setCreatedAt(oldUser.get().getCreatedAt());
            user.setBornDate(oldUser.get().getBornDate());
            return userRepository.save(refreshUser.user());
        }
        return null;
    }
}
