package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

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


    private Converter<User, String> getBornDateConverter() {
        return context -> {
            User user = context.getSource();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(user.getBornDate());
        };

    }
    public UserReturnDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getBornDateConverter());
        modelMapper.map(user, this);
    }
}
