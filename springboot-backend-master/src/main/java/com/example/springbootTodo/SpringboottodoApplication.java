package com.example.springbootTodo;

import com.example.springbootTodo.repository.TodoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoAuditing
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses ={TodoRepository.class})
public class SpringboottodoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringboottodoApplication.class, args);
	}

}
