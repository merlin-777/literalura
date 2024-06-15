package com.x.literalura.domain.dto;

import com.x.literalura.domain.person.Person;

public record AuthorDTO(String name, String birthYear, String deathYear) {

    public AuthorDTO(Person person){
        this(person.getName(),person.getBirthYear(), person.getDeathYear());
    }

    @Override
    public String toString(){
        return String.format("%s (%s, %s)",this.name,this.birthYear,this.deathYear);
    }
}
