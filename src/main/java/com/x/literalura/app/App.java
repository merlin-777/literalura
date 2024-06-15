package com.x.literalura.app;

import com.x.literalura.domain.Language;
import com.x.literalura.domain.book.Book;
import com.x.literalura.domain.book.DataBook;
import com.x.literalura.domain.DataResponse;
import com.x.literalura.domain.dto.AuthorDTO;
import com.x.literalura.domain.dto.BookLanguagesDTO;
import com.x.literalura.domain.person.Person;
import com.x.literalura.infra.service.ApiConsumer;
import com.x.literalura.infra.service.BookService;
import com.x.literalura.infra.service.LanguageService;
import com.x.literalura.infra.service.PersonService;
import com.x.literalura.utils.JsonUtils;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static final String URL_BASE = "https://gutendex.com/books/";

    private final Scanner scanner = new Scanner(System.in);
    final Logger logger = Logger.getLogger(getClass().getName());

    public final ApiConsumer apiConsumer;

    private final BookService bookService;
    private final PersonService personService;
    private final LanguageService languageService;

    public App(BookService bookService, PersonService personService,LanguageService languageService) {
        this.apiConsumer = new ApiConsumer();
        this.bookService = bookService;
        this.personService = personService;
        this.languageService = languageService;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    ------------
                    Elija la opción a travéz de su número:
                    1- buscar libro por título
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    0- salir
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getAllAuthors();
                    break;
                case 4:
                    getLiveAuthorByYear();
                    break;
                case 5:
                    getBooksByLanguage();
                    break;
                case 0:
                    logger.warning(" *** Aplicación finalizada *** ");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void getBooksByLanguage() {
        System.out.println("------------\nElija una opción");
        try{

            List<Language> languages = languageService.findAll();
            showList(languages.stream().map(Language::getCode).toList());
            int opt = scanner.nextInt();
            Language language = languages.get(opt-1);
            List<BookLanguagesDTO> bookDTOList = bookService.getAllByLanguage(language);
            showList(bookDTOList);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getLiveAuthorByYear() {
        try{
            System.out.print("Ingrese un año: ");
            short year = scanner.nextShort();
            System.out.println(" *** Autores vivos en el año "+year+" *** ");
            List<Person> authors = personService.getLiveAuthorsByYear(year);
            showList(authors);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllAuthors() {
        System.out.println(" *** Autores registrados *** ");
        try{
            System.out.println(" Total = "+personService.count());
            List<AuthorDTO> authors = personService.getAllAuthors();
            showList(authors);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllBooks() {
        System.out.println(" *** Libros registrados *** ");
        try{
            List<BookLanguagesDTO> books = bookService.getAll();
            showList(books);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void searchBookByTitle() {

        DataResponse dataResponse = getDataResponse();
        System.out.println("DataResponse: " + dataResponse);
        System.out.println("elementos: "+dataResponse.results().size());

        saveBooksWithAuthors(dataResponse.results());
    }
    private void saveBooksWithAuthors(List<DataBook> books){
        System.out.println(" *** Libros guardados correctamente *** ");
        books.forEach(dataBook -> {
            try{
                Book persistBook = bookService.saveBookWithAuthors(dataBook);

                System.out.println("✓ "+persistBook.getTitle());
            }catch (Exception e){
                System.out.println("Error - "+e.getMessage());
            }
        });
    }

    private DataResponse getDataResponse() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var bookName = scanner.nextLine();
        var json = apiConsumer.fetchData(URL_BASE + "?search=" + bookName.replace(" ", "%20"));
        return JsonUtils.fromJson(json, DataResponse.class);
    }

    public void TestApi(){
        logger.info(" *** TEST LITERALURA *** ");

        try {
            String res = apiConsumer.fetchData(URL_BASE+"84/");
            DataBook dataBook = JsonUtils.fromJson(res, DataBook.class);
            logger.log(Level.INFO, "book response => {0} ", dataBook);
        }catch(Exception e){
            logger.info(" *** error: "+e.getMessage());
        }
    }



    public <T> void showList(List<T> list){
        StringBuilder response = new StringBuilder();
        response.append(" Total = ").append(list.size()).append("\n");
        int showIndex = 1;
        for(T item: list){
            response.append(showIndex).append(".- ").append(item.toString()).append("\n");
            showIndex++;
        }
        System.out.println(response);
    }


}
