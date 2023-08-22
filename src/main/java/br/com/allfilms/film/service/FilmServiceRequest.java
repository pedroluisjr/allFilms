//package br.com.allfilms.film.service;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class FilmServiceRequest {
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://api.themoviedb.org/3/movie/"+userInput)
//            .get()
//            .addHeader("accept", "application/json")
//            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTlkNWU1YmQyMzQxYTdiN2ZlNGMwNGNkZGVlOWQ3YyIsInN1YiI6IjY0ZTU0MDUyZDIzNmU2MDEzYjMxMzNkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VoAaq0GXiMWKBvZ_1tpprmAoo2KRgqQpe4NDvC-g-i4")
//            .build();
//
//    Response response = client.newCall(request).execute();
//
//    public FilmServiceRequest() throws IOException {
//    }
//}
