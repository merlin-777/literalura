package com.x.literalura.infra.repository;

import com.x.literalura.domain.Language;
import com.x.literalura.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String bookName);
    //List<Book> findByTitleContainsIgnoreCaseList(String bookName);


    @Query("SELECT b FROM Book b " +
            "JOIN FETCH b.authors")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b " +
            "JOIN FETCH b.authors " +
            "WHERE b.title LIKE :title")
    Book findBookWithAuthorsByName(@Param("title") String title);
    /*
    * @Query("SELECT b FROM Book b JOIN FETCH b.authors")
public List<Book> findAllWithAuthors();
    * */


   /*
   *  @Query("SELECT b FROM Book b " +
            "JOIN FETCH b.languages l" +
            "WHERE l IN (:languages)")
    List<Book> findAllByLanguageList(@Param("languages") List<Language> languages);
   * */

    List<Book> findAllByLanguages(List<Language> languages);


}
