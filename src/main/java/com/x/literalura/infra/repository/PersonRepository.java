package com.x.literalura.infra.repository;

import com.x.literalura.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByNameIgnoreCase(String bookName);

    @Query("SELECT p FROM Person p WHERE CAST(p.birthYear AS SHORT) <= :year AND (p.deathYear IS NULL OR CAST(p.deathYear AS SHORT)> :year)")
    List<Person> getLiveAuthorByYear(@Param("year") short year);

}
