package br.com.allfilms.film.dto;

import br.com.allfilms.film.model.Historic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricDto {

    private String review;
    private double stars;
    private Long user;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(HistoricDto.class, Historic.class).addMapping(HistoricDto::getUser, Historic::setUser);

        modelMapper.createTypeMap(Historic.class, HistoricDto.class).addMapping(lambda -> lambda.getUser().getId(), HistoricDto::setUser);
    }
    public Historic toHistoric() {
        return modelMapper.map(this, Historic.class);
    }

    public HistoricDto(Historic historic) {
        modelMapper.map(historic, this);
    }

}
