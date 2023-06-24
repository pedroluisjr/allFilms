package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.Data;

@Data

public class RefreshUserDto {
    private String name;
    private String surname;
    private String password;
    private boolean activeUser;

    public User user () {
        User user = new User();
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setPassword(this.password);
        user.setActiveUser(this.activeUser);
        return user;
    }
}
