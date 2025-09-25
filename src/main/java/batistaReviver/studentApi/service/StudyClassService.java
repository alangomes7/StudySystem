package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Course;
import batistaReviver.studentApi.model.Professor;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.repository.CourseRepository;
import batistaReviver.studentApi.repository.ProfessorRepository;
import batistaReviver.studentApi.repository.StudyClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyClassService {

  private final StudyClassRepository studyClassRepository;
  private final CourseRepository courseRepository;
  private final ProfessorRepository professorRepository;

  /**
   * Creates and saves a new class offering (StudyClass).
   *
   * @param year The academic year of the class.
   * @param semester The semester of the class.
   * @param courseId The ID of the course this class belongs to.
   * @param professorId The ID of the professor teaching the class.
   * @return The newly created StudyClass entity.
   * @throws EntityNotFoundException if the course or professor with the given IDs are not found.
   */
  @Transactional
  public StudyClass createStudyClass(int year, int semester, Long courseId, Long professorId) {
    Course course =
        courseRepository
            .findById(courseId)
            .orElseThrow(
                () -> new EntityNotFoundException("Course with id = " + courseId + " not found."));

    Professor professor =
        professorRepository
            .findById(professorId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Professor with id = " + professorId + " not found."));

    StudyClass newStudyClass = new StudyClass(year, semester, course, professor);
    return studyClassRepository.save(newStudyClass);
  }

  /**
   * Deletes a StudyClass by its ID.
   *
   * <p>Note: Due to {@code orphanRemoval=true} in the StudyClass entity, all associated
   * Subscriptions will also be deleted.
   *
   * @param id The ID of the class to delete.
   * @throws EntityNotFoundException if no class with the given ID is found.
   */
  @Transactional
  public void deleteStudyClass(Long id) {
    if (!studyClassRepository.existsById(id)) {
      throw new EntityNotFoundException("StudyClass with id = " + id + " not found.");
    }
    studyClassRepository.deleteById(id);
  }
}
