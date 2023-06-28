package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
public class UserReturnDto {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String bornDate;
    private boolean activeUser;

    public UserReturnDto(User user) {
        new ModelMapper().map(user, UserReturnDto.class);
    }
}
