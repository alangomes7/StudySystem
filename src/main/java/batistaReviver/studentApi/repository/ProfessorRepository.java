package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Professor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
  Optional<Professor> findByRegister(String register);

  Optional<Professor> findByEmail(String email);
}
