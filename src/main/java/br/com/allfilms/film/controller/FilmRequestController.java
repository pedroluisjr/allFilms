package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.FilmRequestByIdDto;
import br.com.allfilms.film.dto.FilmResultDto;
import br.com.allfilms.film.service.FilmRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filmes")
public class FilmRequestController {

    @Autowired
    FilmRequestService filmRequestService;

    @GetMapping("/name/{space}")
    public ResponseEntity<FilmResultDto> getFilm(@PathVariable("space")String space) {
        return filmRequestService.getFilm(space);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FilmRequestByIdDto> getFilmById(@PathVariable("id") Long id) {
        return filmRequestService.getFilmById(id);
    }

}
