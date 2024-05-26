package com.x.literalura.app;

import com.x.literalura.models.DataBook;
import com.x.literalura.service.ApiConsumer;
import com.x.literalura.utils.JsonUtils;

import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    public static final String URL_BASE = "https://gutendex.com/books/";

    private final Scanner scanner = new Scanner(System.in);
    final Logger logger = Logger.getLogger(getClass().getName());

    public final ApiConsumer apiConsumer;

    public App(){
        this.apiConsumer= new ApiConsumer();
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    ------------
                    Elija la ipción a travéz de su número:
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
                    break;
                case 3:
                    break;
                case 0:
                    logger.warning("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void searchBookByTitle() {
        // search in database

        DataBook dataBook = getDataBook();
        // if !existe guardar
    }

    private DataBook getDataBook() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var bookName = scanner.nextLine();
        var json = apiConsumer.fetchData(URL_BASE + bookName.replace(" ", "+") );
        System.out.println(json);
        return JsonUtils.fromJson(json, DataBook.class);
    }
}
