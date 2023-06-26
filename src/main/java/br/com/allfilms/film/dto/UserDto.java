package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserDto implements UserDetails {
    @NotBlank(message = "Login não pode estar em branco")
    @Size(min = 3, max = 20)
    private String login;

    @NotBlank(message = "Senha não pode estar em branco")
    private String password;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email não pode estar em branco")
    private String email;

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;

    @NotBlank(message = "Sobrenome não pode estar em branco")
    private String surname;

    @NotNull(message = "Data de aniversário não pode estar em branco")
    private String bornDate;

    public User user () throws ParseException {
        User user = new User();
        user.setName(this.name);
        user.setPassword(this.password);
        user.setSurname(this.surname);
        user.setLogin(this.login);
        user.setEmail(this.email);
        user.setActiveUser(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date formatedDate = dateFormat.parse(this.bornDate);
        user.setBornDate(formatedDate);
        return user;
    }

    //TODO: Implementar JWT para autenticação via Token.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
