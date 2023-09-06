package br.com.allfilms.film.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRequestDto {

    private Long id;
    private String original_language;
    private String original_title;
    private double vote_average;
    private int vote_count;

}
