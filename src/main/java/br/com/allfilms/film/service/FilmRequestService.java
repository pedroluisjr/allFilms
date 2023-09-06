package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.FilmQueryDto;
import br.com.allfilms.film.dto.FilmRequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmRequestService {

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTlkNWU1YmQyMzQxYTdiN2ZlNGMwNGNkZGVlOWQ3YyIsInN1YiI6IjY0ZTU0MDUyZDIzNmU2MDEzYjMxMzNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VoAaq0GXiMWKBvZ_1tpprmAoo2KRgqQpe4NDvC-g-i4";

    private String urlFilmApi = "https://api.themoviedb.org/3";

    //TODO: Realizar a consulta da API
    public ResponseEntity<FilmRequestDto> getFilm(FilmQueryDto filmQueryDto) {

        String space = filmQueryDto.getQuery();
        space = space.replace(" ", "%20");

        HttpHeaders header = new HttpHeaders();
                header.add("authorization", "Bearer "+ token);

        RestTemplate rest = new RestTemplate();

        ResponseEntity<FilmRequestDto> filmResponse = rest.exchange(urlFilmApi + "/search/movie?query=" + space,
                HttpMethod.GET, new HttpEntity<Object>(header), FilmRequestDto.class);

        if (filmResponse.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(filmResponse.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
