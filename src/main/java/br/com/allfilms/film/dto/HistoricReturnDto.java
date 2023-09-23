package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricReturnDto {

    private User user;
    private FilmRequestByIdDto filmRequestByIdDto;
    private HistoricDto historicDto;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(HistoricReturnDto.class, Historic.class).addMapping(HistoricReturnDto::getUser, Historic::setUser);

        modelMapper.createTypeMap(Historic.class, HistoricReturnDto.class).addMapping(lambda -> lambda.getUser().getId(), HistoricReturnDto::setUser);

        modelMapper.createTypeMap(HistoricReturnDto.class, Historic.class).addMapping(HistoricReturnDto::getHistoricDto, Historic::setHistoricId);

        modelMapper.createTypeMap(Historic.class, HistoricReturnDto.class).addMapping(Historic::getHistoricId, HistoricReturnDto::setHistoricDto);

    }

    public HistoricReturnDto(Historic historic) {
        modelMapper.map(historic, this);
    }

    public Historic toHistoric() {
        return modelMapper.map(this, Historic.class);
    }

}
