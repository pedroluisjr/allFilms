package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String loginMail;
    private String password;
    private String name;
    private String surname;
    private String bornDate;

    public User user (){
        User user = new User();
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        user.setSurname(this.getSurname());
        user.setLoginMail(this.getLoginMail());
        user.setBornDate(this.getBornDate());
        return user;
    }

}
