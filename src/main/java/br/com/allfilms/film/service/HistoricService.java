package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoricService {

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<Historic> addHistoric(HistoricDto historicDto) {
        Historic saveHistoric = historicDto.toHistoric();

        Optional<User> existUser = userService.
    }

    public Page<HistoricDto> getHistoric(Pageable pageable) {
        return historicRepository.findAll(pageable).map(HistoricDto::new);
    }

}
