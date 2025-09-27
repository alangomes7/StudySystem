package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.StudyClass;
import java.util.List;
import java.util.Optional; // Import Optional
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link StudyClass} entity.
 *
 * <p>This interface allows for CRUD operations and other data access patterns for StudyClass
 * entities without requiring manual implementation.
 */
@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Long> {

  /**
   * Finds all study classes taught by a specific professor.
   *
   * @param professorId The ID of the professor.
   * @return A list of {@link StudyClass} entities taught by the professor.
   */
  List<StudyClass> findByProfessorId(Long professorId);

  /**
   * Finds a study class by its name, ignoring case.
   *
   * @param name The name of the class to find.
   * @return An {@link Optional} containing the found class, or empty if not found.
   */
  Optional<StudyClass> findByNameIgnoreCase(String name);

  /**
   * Checks if a study class with the given name already exists, ignoring case. This is more
   * efficient than fetching the full entity if you only need to check for existence.
   *
   * @param name The name of the class to check.
   * @return {@code true} if a class with that name exists, {@code false} otherwise.
   */
  boolean existsByNameIgnoreCase(String name);
}
