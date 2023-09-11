package br.com.allfilms.film.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "original_title",
        "original_language",
        "adult",
        "popularity",
        "runtime",
        "overview",
        "release_date",
        "status",
        "vote_average",
        "vote_count"
})
public class FilmRequestByIdDto {

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("original_language")
    private String original_language;

    @JsonProperty("original_title")
    private String original_title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private double popularity;

    @JsonProperty("release_date")
    private String release_date;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("status")
    private String status;

    @JsonProperty("title")
    private String title;

    @JsonProperty("vote_average")
    private double vote_average;

    @JsonProperty("vote_count")
    private int vote_count;

}
