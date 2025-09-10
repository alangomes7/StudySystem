package com.alangomes.students.repository;


import com.alangomes.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegister(String register);
    Optional<Student> findByEmail(String email);
}