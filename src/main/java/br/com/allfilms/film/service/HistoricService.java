package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HistoricService {

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<Historic> addHistoric(HistoricDto historicDto) {
        Historic saveHistoric = historicDto.toHistoric();

        User existUser = userService.getUserById(historicDto.getUser()).orElseThrow(NoSuchElementException::new);
        if (existUser != null) {
            saveHistoric.setUser(existUser);
            historicRepository.save(saveHistoric);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Page<HistoricDto> getHistoric(Pageable pageable) {
        return historicRepository.findAll(pageable).map(HistoricDto::new);
    }

    public List<Historic> getHistoricId(Long id) {
        Optional<User> existUserId = userService.getUserById(id);
        if (existUserId.isPresent()) {
            User existUser = existUserId.get();
            return historicRepository.findByUser(existUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
