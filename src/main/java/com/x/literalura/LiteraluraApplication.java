package com.x.literalura;

import com.x.literalura.app.App;
import com.x.literalura.infra.service.BookService;
import com.x.literalura.infra.service.LanguageService;
import com.x.literalura.infra.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	private final BookService bookService;
	private final PersonService personService;
	private final LanguageService languageService;

	public LiteraluraApplication(BookService bookService, PersonService personService,LanguageService languageService) {
		this.bookService=bookService;
		this.personService=personService;
		this.languageService=languageService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App app = new App(bookService,personService,languageService);
		app.showMenu();

	}
}
