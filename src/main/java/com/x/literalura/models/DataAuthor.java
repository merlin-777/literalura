package com.x.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(
        String name,
        @JsonAlias("birth_year") short birthYear,
        @JsonAlias("death_year") short deathYear
) {
}
