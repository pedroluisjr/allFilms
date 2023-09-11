package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricReturnDto {

    private User user;
    private FilmRequestByIdDto filmRequestByIdDto;
    private Historic historic;

}
