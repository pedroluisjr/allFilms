package br.com.allfilms.film.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

    private Long id;
    private String originalLanguage;
    private String originalTitle;
    private double voteAverage;
    private int voteCount;

}
