package com.example.doroti.CRUD_guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class CrudGuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudGuestbookApplication.class, args);
	}

}
