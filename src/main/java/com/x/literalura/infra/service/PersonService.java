package com.x.literalura.infra.service;

import com.x.literalura.domain.dto.AuthorDTO;
import com.x.literalura.infra.repository.PersonRepository;
import com.x.literalura.domain.person.DataPerson;
import com.x.literalura.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person saveAuthor(DataPerson author) throws Exception{
        Optional<Person> existingAuthor = personRepository.findByNameIgnoreCase(author.name());

        if( existingAuthor.isEmpty()){
            return personRepository.save(new Person(author));
        }
        else{
            throw new Exception("el Autor ya existe");
        }
    }

    public Optional<Person> getAuthorByName(String name){
        return personRepository.findByNameIgnoreCase(name);
    }

    public List<AuthorDTO> getAllAuthors(){
        return personRepository.findAll().stream().map(AuthorDTO::new).toList();
    }

    public List<Person> getLiveAuthorsByYear(short year) {
        return personRepository.getLiveAuthorByYear(year);
    }

    public long count() {
        return personRepository.count();
    }
}
