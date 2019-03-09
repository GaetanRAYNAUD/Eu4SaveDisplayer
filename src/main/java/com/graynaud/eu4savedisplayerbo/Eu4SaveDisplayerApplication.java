package com.graynaud.eu4savedisplayerbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.graynaud.eu4savedisplayerbo.repository")
@SpringBootApplication
public class Eu4SaveDisplayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Eu4SaveDisplayerApplication.class, args);
	}

}
