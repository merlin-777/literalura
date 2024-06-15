package com.x.literalura.infra.service;

import com.x.literalura.domain.Language;
import com.x.literalura.domain.dto.BookAuthorDTO;
import com.x.literalura.domain.dto.BookLanguagesDTO;
import com.x.literalura.domain.book.Book;
import com.x.literalura.domain.book.DataBook;
import com.x.literalura.domain.person.DataPerson;
import com.x.literalura.domain.person.Person;
import com.x.literalura.infra.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private LanguageService languageService;


    public BookAuthorDTO getByName(String bookName) {
        Optional<Book> book = bookRepository.findByTitleContainsIgnoreCase(bookName);
        return book.map(BookAuthorDTO::new).orElse(null);
    }

    public Book saveBookWithAuthors(DataBook dataBook) throws Exception{

        // valid book
        Optional<Book> existingBook =bookRepository.findByTitleContainsIgnoreCase(dataBook.title());
        if (existingBook.isPresent()) {
            throw new Exception("ya existe él libro "+dataBook.title().substring(0,15).concat("..."));
        }

        // add only Book
        Book persistBook = bookRepository.save(new Book(dataBook.title()));

        // get authors
        Set<Person> finalAuthors = new HashSet<>();
        for (DataPerson author : dataBook.authors()) {
            if (author.name() != null) {
                Optional<Person> existingAuthor =personService.getAuthorByName(author.name());
                //valid
                if(existingAuthor.isPresent()){
                    finalAuthors.add(existingAuthor.get());
                }else{
                    finalAuthors.add(personService.saveAuthor(author));
                }
            }
        }

        // get languages
        Set<Language> finalLanguages = new HashSet<>();
        for (String languageName: dataBook.languages()){
            Optional<Language> existingLanguage =languageService.getLanguageByCode(languageName);
            //valid
            if(existingLanguage.isPresent()){
                finalLanguages.add(existingLanguage.get());
            }else{
                finalLanguages.add(languageService.saveLanguage( new Language(languageName) ));
            }
        }

        // set relations
        persistBook.setAuthors(finalAuthors);
        persistBook.setLanguages(finalLanguages);
        // save book and relations
        return bookRepository.save(persistBook);
    }

    public List<Book> getAllWithAuthors() throws Exception{
        return bookRepository.findAllWithAuthors();
    }

    @Transactional
    public List<BookLanguagesDTO> getAll() throws Exception{
        return bookRepository.findAll().stream().map(book -> new BookLanguagesDTO(book, book.getLanguages()) ).toList();
    }

    public List<BookLanguagesDTO> getAllByLanguage(Language language){
        List<Language> languages = new ArrayList<>();
        languages.add(language);
        return bookRepository.findAllByLanguages(languages).stream().map(BookLanguagesDTO::new).toList();
    }
}
