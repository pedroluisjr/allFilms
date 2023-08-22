package br.com.allfilms.film.controller;

import br.com.allfilms.film.dto.FilmDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filme")
public class FilmController {

    @GetMapping("/{id}")
    public List<FilmDto> getFilme(Long id) {
        return null;
    }

}
