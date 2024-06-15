package com.x.literalura.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.x.literalura.domain.book.DataBook;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataResponse(
        @JsonAlias("results") List<DataBook> results
) {
}
