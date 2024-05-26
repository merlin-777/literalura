package com.x.literalura.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
public class Book {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();
}
