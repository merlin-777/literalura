package com.x.literalura.domain.book;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.x.literalura.domain.person.DataPerson;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        String title,
        //@JsonAlias("download_count") Integer downloadCount,
        @JsonAlias("authors") List<DataPerson> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") int downloadCount
) {
}
