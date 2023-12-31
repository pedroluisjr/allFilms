package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.dto.HistoricReturnDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.service.HistoricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class HistoricController {

    @Autowired
    HistoricService historicService;

    @PostMapping
    public ResponseEntity<Historic> addHistoric(@RequestBody HistoricDto historicDto) {
        return historicService.addHistoric(historicDto);
    }

    @GetMapping
    public Page<HistoricDto> getHistoric(Pageable pageable) {
        return historicService.getHistoric(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricReturnDto> getHistoricId(@PathVariable("id") Long id) {
        return (historicService.getHistoricId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Historic> attHistoric(@PathVariable("id") Long id, @RequestBody HistoricDto historicDto) {
        return historicService.attHistoric(id, historicDto);
    }

}
