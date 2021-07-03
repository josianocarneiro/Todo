package com.jcsis.todo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jcsis.todo.domain.Todo;
import com.jcsis.todo.repositories.TodoRepository;

@SpringBootApplication
public class TodoApplication  implements CommandLineRunner{
	@Autowired
	private TodoRepository TodoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		Todo t1 = new Todo(null, "Estudar", "Estudar Springboot e Angular 11", sdf.parse("10/07/2021 00:00"), false);

		TodoRepository.saveAll(Arrays.asList(t1));
	}

}
