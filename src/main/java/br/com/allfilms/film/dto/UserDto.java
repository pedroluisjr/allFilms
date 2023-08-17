package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
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

    public User toUser() throws ParseException {
        return new ModelMapper().map(this, User.class);
    }
}
