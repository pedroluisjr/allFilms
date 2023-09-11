package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.FilmRequestByIdDto;
import br.com.allfilms.film.dto.FilmRequestDto;
import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.dto.HistoricReturnDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricService {

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTlkNWU1YmQyMzQxYTdiN2ZlNGMwNGNkZGVlOWQ3YyIsInN1YiI6IjY0ZTU0MDUyZDIzNmU2MDEzYjMxMzNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VoAaq0GXiMWKBvZ_1tpprmAoo2KRgqQpe4NDvC-g-i4";

    private String urlFilmApi = "https://api.themoviedb.org/3";

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    FilmRequestService filmRequestService;

    @Autowired
    UserService userService;

    public ResponseEntity<Historic> addHistoric(HistoricDto historicDto) {
        userService.getUserById(historicDto.getUser());
        historicRepository.save(historicDto.toHistoric());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Page<HistoricDto> getHistoric(Pageable pageable) {
        return historicRepository.findAll(pageable).map(HistoricDto::new);
    }

    public List<HistoricReturnDto> getHistoricId(Long id) {

        Optional<Historic> savedHist = historicRepository.findById(id);

        HistoricReturnDto historicReturnDto = new HistoricReturnDto();

        if (savedHist.isPresent()) {
            userService.getUserById(historicReturnDto.getUser().getId());
            filmRequestService.getFilmById(historicReturnDto.getHistoric().getMovieId());
            return null;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Historic> attHistoric(Long id, HistoricDto historicDto) {
        Optional<Historic> savedHistoric = historicRepository.findById(id);
        if (savedHistoric.isPresent()) {
            if (historicDto.getReview() != null) savedHistoric.get().setReview(historicDto.getReview());

            userService.getUserById(historicDto.getUser()).ifPresent((user ->
                    savedHistoric.get().setUser(historicDto.toHistoric().getUser())));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}


