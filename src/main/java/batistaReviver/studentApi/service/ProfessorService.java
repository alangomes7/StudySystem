package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Professor;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.repository.ProfessorRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

  private final ProfessorRepository professorRepository;

  /**
   * Creates and saves a new professor.
   *
   * @param professor The professor object to be created.
   * @return The saved professor with its generated ID.
   */
  @Transactional
  public Professor createProfessor(Professor professor) {
    return professorRepository.save(professor);
  }

  /**
   * Retrieves a list of all professors.
   *
   * @return A list containing all professor entities.
   */
  @Transactional(readOnly = true)
  public List<Professor> getAllProfessors() {
    return professorRepository.findAll();
  }

  /**
   * Retrieves a single professor by their ID.
   *
   * @param id The ID of the professor to retrieve.
   * @return The found professor.
   * @throws EntityNotFoundException if no professor with the given ID is found.
   */
  @Transactional(readOnly = true)
  public Professor getProfessorById(Long id) {
    return professorRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Professor with id = " + id + " not found."));
  }

  /**
   * Updates an existing professor's information.
   *
   * @param id The ID of the professor to update.
   * @param professorDetails The professor object containing the new details.
   * @return The updated professor.
   * @throws EntityNotFoundException if the professor to update is not found.
   */
  @Transactional
  public Professor updateProfessor(Long id, Professor professorDetails) {
    Professor existingProfessor = getProfessorById(id);
    existingProfessor.setName(professorDetails.getName());
    existingProfessor.setPhone(professorDetails.getPhone());
    existingProfessor.setEmail(professorDetails.getEmail());
    existingProfessor.setRegister(professorDetails.getRegister());
    return professorRepository.save(existingProfessor);
  }

  /**
   * Deletes a professor by their ID.
   *
   * @param id The ID of the professor to delete.
   * @throws EntityNotFoundException if the professor to delete is not found.
   */
  @Transactional
  public void deleteProfessor(Long id) {
    if (!professorRepository.existsById(id)) {
      throw new EntityNotFoundException("Professor with id = " + id + " not found.");
    }
    professorRepository.deleteById(id);
  }

  /**
   * Retrieves the teaching history (all classes) for a specific professor.
   *
   * @param id The ID of the professor.
   * @return A set of StudyClass objects taught by the professor.
   * @throws EntityNotFoundException if the professor is not found.
   */
  @Transactional(readOnly = true)
  public Set<StudyClass> getProfessorHistory(Long id) {
    Professor professor = getProfessorById(id);
    return professor.getStudyClasses();
  }
}
