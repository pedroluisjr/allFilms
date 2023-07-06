package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReturnDto {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String bornDate;
    private boolean activeUser;
    
    public UserReturnDto(User user) {
        new ModelMapper().map(user, this);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.bornDate = dateFormat.format(user.getBornDate());
    }
}
