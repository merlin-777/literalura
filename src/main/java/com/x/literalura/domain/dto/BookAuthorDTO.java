package com.x.literalura.domain.dto;

import com.x.literalura.domain.book.Book;

import java.util.List;
import java.util.stream.Collectors;

public record BookAuthorDTO(Long id, String title, List<AuthorDTO> authors) {

    public BookAuthorDTO(Book book){
        this(book.getId(), book.getTitle(), book.getAuthors().stream().map(AuthorDTO::new).toList() );
    }
}
