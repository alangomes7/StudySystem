package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.Professor;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.service.ProfessorService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professors")
@RequiredArgsConstructor
public class ProfessorController {

  private final ProfessorService professorService;

  /**
   * POST /professors : Creates a new professor.
   *
   * @param professor The professor to create.
   * @return A response entity with the created professor and HTTP status 201 (Created).
   */
  @PostMapping
  public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
    Professor createdProfessor = professorService.createProfessor(professor);
    return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
  }

  /**
   * GET /professors : Retrieves all professors.
   *
   * @return A response entity with a list of all professors and HTTP status 200 (OK).
   */
  @GetMapping
  public ResponseEntity<List<Professor>> getAllProfessors() {
    return ResponseEntity.ok(professorService.getAllProfessors());
  }

  /**
   * GET /professors/{id} : Retrieves a specific professor by their ID.
   *
   * @param id The ID of the professor to retrieve.
   * @return A response entity with the found professor and HTTP status 200 (OK).
   */
  @GetMapping("/{id}")
  public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
    return ResponseEntity.ok(professorService.getProfessorById(id));
  }

  /**
   * PUT /professors/{id} : Updates an existing professor.
   *
   * @param id The ID of the professor to update.
   * @param professorDetails The new details for the professor.
   * @return A response entity with the updated professor and HTTP status 200 (OK).
   */
  @PutMapping("/{id}")
  public ResponseEntity<Professor> updateProfessor(
      @PathVariable Long id, @RequestBody Professor professorDetails) {
    return ResponseEntity.ok(professorService.updateProfessor(id, professorDetails));
  }

  /**
   * DELETE /professors/{id} : Deletes a professor by their ID.
   *
   * @param id The ID of the professor to delete.
   * @return A response entity with no content and HTTP status 204 (No Content).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
    professorService.deleteProfessor(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * GET /professors/{id}/history : Retrieves the teaching history for a specific professor.
   *
   * @param id The ID of the professor.
   * @return A response entity with a set of classes taught by the professor and HTTP status 200
   *     (OK).
   */
  @GetMapping("/{id}/history")
  public ResponseEntity<Set<StudyClass>> getProfessorHistory(@PathVariable Long id) {
    return ResponseEntity.ok(professorService.getProfessorHistory(id));
  }
}
