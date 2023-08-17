package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.service.HistoricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historico")
public class HistoricController {

    @Autowired
    HistoricService historicService;

    @PostMapping
    public ResponseEntity<Historic> addHistoric(@RequestBody HistoricDto historicDto) {
        return null;
    }

    @GetMapping
    public Page<HistoricDto> getHistoric(Pageable pageable) {
        return historicService.getHistoric(pageable);
    }

}
