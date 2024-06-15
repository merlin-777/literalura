package com.x.literalura.domain.dto;

import com.x.literalura.domain.Language;
import com.x.literalura.domain.book.Book;

import java.util.Set;
import java.util.stream.Collectors;

public record BookLanguagesDTO(Long id, String title, Set<String> languages){
    public BookLanguagesDTO(Book book, Set<Language> languages){
        this(book.getId(), book.getTitle(), languages.stream().map(Language::getCode).collect(Collectors.toSet()));
    }

    public BookLanguagesDTO(Book book){
        this(book.getId(), book.getTitle(), book.getLanguages().stream().map(Language::getCode).collect(Collectors.toSet()));
    }

    @Override
    public String toString(){
        return this.languages+" - "+this.title;
    }
}
