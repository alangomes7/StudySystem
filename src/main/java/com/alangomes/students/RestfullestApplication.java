package com.alangomes.students;

import com.alangomes.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class RestfullestApplication implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public static void main(String[] args) {
		SpringApplication.run(RestfullestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
