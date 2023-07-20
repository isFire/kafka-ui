package com.manager.kafka;

import io.swagger.v3.oas.models.annotations.OpenAPI31;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@EnableWebFlux
@OpenAPI31
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		log.info("\n----------------------------------------------------------\n\t" +
//				"Application '{}' is running! Access URLs:\n\t" +
//				"Local: \t\thttp://localhost:{}\n\t" +
//				"External: \thttp://{}:{}\n\t"+
//				"Doc: \thttp://{}:{}/doc.html\n"+
//				"----------------------------------------------------------",
	}
}
