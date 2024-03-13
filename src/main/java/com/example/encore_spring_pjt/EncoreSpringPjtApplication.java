package com.example.encore_spring_pjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// https://github.com/jslim9413/encore_spring_pjt.git 

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) 
public class EncoreSpringPjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncoreSpringPjtApplication.class, args);
	}

}
