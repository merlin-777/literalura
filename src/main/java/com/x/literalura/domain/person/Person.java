package com.x.literalura.domain.person;

import com.x.literalura.domain.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="persons")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String birthYear;
    private String deathYear;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(
            name = "person_book",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    public Person(DataPerson authors) {
        this.name=authors.name();
        this.birthYear=authors.birthYear();
        this.deathYear= authors.deathYear();
    }

    @Override
    public String toString(){
        return "Author(id="+this.getId()+", name= "+this.name+", birthYear= "+this.birthYear+", deathYear= "+this.deathYear+")";
    }
}
