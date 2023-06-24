package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserDto {
    private String loginMail;
    private String password;
    private String name;
    private String surname;
    private String bornDate;

    public User user () throws ParseException {
        User user = new User();
        user.setName(this.name);
        user.setPassword(this.password);
        user.setSurname(this.surname);
        user.setLoginMail(this.loginMail);
        user.setActiveUser(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date formatedDate = dateFormat.parse(this.bornDate);
        user.setBornDate(formatedDate);
        return user;
    }
}
