package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Course;
import batistaReviver.studentApi.model.Professor;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.repository.CourseRepository;
import batistaReviver.studentApi.repository.ProfessorRepository;
import batistaReviver.studentApi.repository.StudyClassRepository;
import java.util.List; // Import List
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
   * Note: For the createStudyClass validation to work, you first need to add this method to your
   * {@code StudyClassRepository}: {@code boolean existsByCourseIdAndYearAndSemester(Long courseId,
   * int year, int semester);}
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
   * Retrieves all study classes.
   *
   * @return A list of all {@link StudyClass} entities.
   */
  public List<StudyClass> getAllStudyClasses() {
    return studyClassRepository.findAll();
  }

  /**
   * Retrieves a single study class by its ID.
   *
   * @param id The ID of the class.
   * @return The found {@link StudyClass} entity.
   * @throws EntityNotFoundException if no class is found with the given ID.
   */
  public StudyClass getStudyClassById(Long id) {
    return studyClassRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("StudyClass with id = " + id + " not found."));
  }

  /**
   * Retrieves all study classes taught by a specific professor.
   *
   * @param professorId The ID of the professor.
   * @return A list of {@link StudyClass} entities.
   * @throws EntityNotFoundException if no professor is found with the given ID.
   */
  public List<StudyClass> getClassesByProfessor(Long professorId) {
    if (!professorRepository.existsById(professorId)) {
      throw new EntityNotFoundException("Professor with id = " + professorId + " not found.");
    }
    return studyClassRepository.findByProfessorId(professorId);
  }

  /**
   * Deletes a StudyClass by its ID.
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
