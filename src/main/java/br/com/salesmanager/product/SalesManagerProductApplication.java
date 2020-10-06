package br.com.salesmanager.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SalesManagerProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagerProductApplication.class, args);
	}

}
