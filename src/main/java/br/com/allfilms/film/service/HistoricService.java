package br.com.allfilms.film.service;

import br.com.allfilms.film.dto.HistoricDto;
import br.com.allfilms.film.model.Historic;
import br.com.allfilms.film.model.User;
import br.com.allfilms.film.repository.HistoricRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HistoricService {

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<Historic> addHistoric(HistoricDto historicDto) throws Exception {
        Historic saveHistoric = historicDto.toHistoric();

        User existUser = userService.getUserById(historicDto.getUser()).orElseThrow(NoSuchElementException::new);
        if (existUser != null) {
            saveHistoric.setUser(existUser);

            //**API EXTERNA

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/" + historicDto.getId() + "?language=en-US")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTlkNWU1YmQyMzQxYTdiN2ZlNGMwNGNkZGVlOWQ3YyIsInN1YiI6IjY0ZTU0MDUyZDIzNmU2MDEzYjMxMzNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VoAaq0GXiMWKBvZ_1tpprmAoo2KRgqQpe4NDvC-g-i4")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    InputStream inputStream = responseBody.byteStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(inputStreamReader);

                    String line;
                    StringBuilder responseContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }

                    //**API EXTERNA

                    historicRepository.save(saveHistoric);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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


