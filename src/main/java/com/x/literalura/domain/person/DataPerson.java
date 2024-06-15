package com.x.literalura.domain.person;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataPerson(
        String name,
        @JsonAlias("birth_year") String birthYear,
        @JsonAlias("death_year") String deathYear
) {
    public DataPerson(Person person){
        this(person.getName(),person.getBirthYear(),person.getDeathYear());
    }
}
