package batistaReviver.studentApi;

import batistaReviver.studentApi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Boot application. It implements {@link CommandLineRunner} to
 * execute code on startup.
 */
@RequiredArgsConstructor
@SpringBootApplication
public class RestfullestApplication implements CommandLineRunner {

  private final StudentRepository studentRepository;

  /**
   * The main method which serves as the entry point for the application.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(RestfullestApplication.class, args);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {}
}
