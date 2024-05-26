package com.x.literalura;

import com.x.literalura.app.App;
import com.x.literalura.models.DataBook;
import com.x.literalura.service.ApiConsumer;
import com.x.literalura.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public final ApiConsumer apiConsumer;

	Logger logger = Logger.getLogger(getClass().getName());

	public LiteraluraApplication(ApiConsumer apiConsumer) {
		this.apiConsumer = apiConsumer;
	}

	public static final String URL = "https://gutendex.com/books/";

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("INICIOs");

		try {
			String res = apiConsumer.fetchData(URL+"84/");
			DataBook dataBook = JsonUtils.fromJson(res, DataBook.class);
			logger.log(Level.INFO, "book response => {0} ", dataBook);
		}catch(Exception e){
			logger.info("imagino q error: "+e.getMessage());
		}


		App app = new App();
		app.showMenu();

	}
}
