package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Student} entity.
 *
 * <p>This interface provides methods for CRUD operations and custom queries for managing {@link
 * Student} entities. It includes methods to find students by their unique register number or email
 * address, which are crucial for validation and retrieval logic.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  /**
   * Finds a student by their unique registration number.
   *
   * @param register The registration number to search for.
   * @return An {@link Optional} containing the found {@link Student}, or an empty optional if no
   *     student is found with the given register.
   */
  Optional<Student> findByRegister(String register);

  /**
   * Finds a student by their unique email address.
   *
   * @param email The email address to search for.
   * @return An {@link Optional} containing the found {@link Student}, or an empty optional if no
   *     student is found with the given email.
   */
  Optional<Student> findByEmail(String email);
}
