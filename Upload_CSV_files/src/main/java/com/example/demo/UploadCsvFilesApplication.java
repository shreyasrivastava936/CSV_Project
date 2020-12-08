package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.example.csv.CSVController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@EnableJpaRepositories (basePackages = {"com.example"})
@EntityScan(basePackages = {"com.example"})
public class UploadCsvFilesApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(UploadCsvFilesApplication.class, args);
	}

}
