package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.FilmRequestByIdDto;
import br.com.allfilms.film.dto.FilmResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmRequestService {

    RestTemplate rest = new RestTemplate();

    HttpHeaders header = new HttpHeaders();

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTlkNWU1YmQyMzQxYTdiN2ZlNGMwNGNkZGVlOWQ3YyIsInN1YiI6IjY0ZTU0MDUyZDIzNmU2MDEzYjMxMzNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VoAaq0GXiMWKBvZ_1tpprmAoo2KRgqQpe4NDvC-g-i4";

    private String urlFilmApi = "https://api.themoviedb.org/3";

    //TODO: Realizar a consulta da API
    public ResponseEntity<FilmResultDto> getFilm(String space) {

        space = space.replace(" ", "%20");

                header.add("Authorization", "Bearer "+ token);

        ResponseEntity<FilmResultDto> filmResponse = rest.exchange(urlFilmApi + "/search/movie?query=" + space +"&language=pt-BR",
                HttpMethod.GET, new HttpEntity<Object>(header), FilmResultDto.class);

        if (filmResponse.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(filmResponse.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<FilmRequestByIdDto> getFilmById(Long id) {

        header.add("Authorization", "Bearer "+ token);

        ResponseEntity<FilmRequestByIdDto> filmResponse = rest.exchange(urlFilmApi + "/movie/" + id +"?language=pt-BR",
                HttpMethod.GET, new HttpEntity<Object>(header), FilmRequestByIdDto.class);

        if (filmResponse.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(filmResponse.getBody());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
