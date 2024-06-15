package com.x.literalura.domain;


import com.x.literalura.domain.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="languages")
@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
@ToString
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String code;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "books_languages",
            joinColumns = @JoinColumn(name = "language_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    public Language(String code) {
        this.code=code;
    }

    public Language(String code, String name) {
        this.code=code;
        this.name=name;
    }

}