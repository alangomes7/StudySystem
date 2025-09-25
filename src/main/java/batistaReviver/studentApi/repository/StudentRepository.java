package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByRegister(String register);

  Optional<Student> findByEmail(String email);
}
