package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.StudyClass;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link StudyClass} entity.
 *
 * <p>This interface provides the mechanism for storage, retrieval, and search behavior for
 * StudyClass entities.
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

  boolean existsByProfessorId(Long id);

  /**
   * Checks if a class for the same course, year, and semester already exists.
   *
   * @param courseId The ID of the course.
   * @param year The academic year.
   * @param semester The semester.
   * @return {@code true} if a class already exists, {@code false} otherwise.
   */
  boolean existsByCourseIdAndYearAndSemester(Long courseId, int year, int semester);
}
