package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Professor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Professor} entity.
 *
 * <p>This interface provides methods for CRUD operations and custom queries for managing {@link
 * Professor} entities. It includes methods to find professors by their unique register number or
 * email address, which are essential for validation and retrieval logic.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
  /**
   * Finds a professor by their unique registration number.
   *
   * @param register The registration number to search for.
   * @return An {@link Optional} containing the found {@link Professor}, or an empty optional if no
   *     professor is found with the given register.
   */
  Optional<Professor> findByRegister(String register);

  /**
   * Finds a professor by their unique email address.
   *
   * @param email The email address to search for.
   * @return An {@link Optional} containing the found {@link Professor}, or an empty optional if no
   *     professor is found with the given email.
   */
  Optional<Professor> findByEmail(String email);
}
