package br.com.allfilms.film.service;

import br.com.allfilms.film.Security.UserDetailsSecurity;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository repository;

    public UserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário: "+username+" não existe");
        }

        return new UserDetailsSecurity(user.get()) {
        };
    }
}
