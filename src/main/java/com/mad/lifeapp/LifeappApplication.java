package com.mad.lifeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LifeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeappApplication.class, args);
	}

}
