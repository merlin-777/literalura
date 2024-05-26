package com.x.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        String title,
        @JsonAlias("download_count") Integer downloadCount,
        @JsonAlias("authors") List<DataAuthor> authors,
        @JsonAlias("languages") List<Language> languages
) {
}
