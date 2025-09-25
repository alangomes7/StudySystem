package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.service.StudyClassService;
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
   * POST /study-classes : Creates a new class.
   *
   * @param request The request body containing class details and related entity IDs.
   * @return A response entity with the created class and HTTP status 201 (Created).
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
   * @return A response entity with no content and HTTP status 204 (No Content).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudyClass(@PathVariable Long id) {
    studyClassService.deleteStudyClass(id);
    return ResponseEntity.noContent().build();
  }
}
