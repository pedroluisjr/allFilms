package br.com.allfilms.film.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class FilmRequestDto {

                private boolean adult;
                private int id;
                private String original_language;
                private String overview;
                private double popularity;
                private String release_date;
                private String title;
                private double vote_average;
                private int vote_count;

}
