package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.service.StudyClassService;
import java.util.List; // Import List
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study-classes")
@RequiredArgsConstructor
public class StudyClassController {

  private final StudyClassService studyClassService;

  /** DTO for creating a new StudyClass. */
  public record CreateStudyClassRequest(int year, int semester, Long courseId, Long professorId) {}

  /**
   * GET /study-classes : Retrieves all classes. Can be filtered by a professor's ID.
   *
   * @param professorId (Optional) The ID of the professor to filter classes by.
   * @return A list of classes and HTTP status 200 (OK).
   */
  @GetMapping
  public ResponseEntity<List<StudyClass>> findStudyClasses(
      @RequestParam(required = false) Long professorId) {
    List<StudyClass> classes;
    if (professorId != null) {
      classes = studyClassService.getClassesByProfessor(professorId);
    } else {
      classes = studyClassService.getAllStudyClasses();
    }
    return ResponseEntity.ok(classes);
  }

  /**
   * GET /study-classes/{id} : Retrieves a specific class by its ID.
   *
   * @param id The ID of the class to retrieve.
   * @return The found class and HTTP status 200 (OK).
   */
  @GetMapping("/{id}")
  public ResponseEntity<StudyClass> getStudyClassById(@PathVariable Long id) {
    StudyClass studyClass = studyClassService.getStudyClassById(id);
    return ResponseEntity.ok(studyClass);
  }

  /**
   * POST /study-classes : Creates a new class.
   *
   * @param request The request body containing class details.
   * @return The created class and HTTP status 201 (Created).
   */
  @PostMapping
  public ResponseEntity<StudyClass> createStudyClass(@RequestBody CreateStudyClassRequest request) {
    StudyClass createdClass =
        studyClassService.createStudyClass(
            request.year(), request.semester(), request.courseId(), request.professorId());
    return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
  }

  /**
   * DELETE /study-classes/{id} : Deletes a class by its ID.
   *
   * @param id The ID of the class to delete.
   * @return No content and HTTP status 204 (No Content).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudyClass(@PathVariable Long id) {
    studyClassService.deleteStudyClass(id);
    return ResponseEntity.noContent().build();
  }
}
