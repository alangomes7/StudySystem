package com.alangomes.students.service;

import com.alangomes.students.exception.EntityNotFoundException;
import com.alangomes.students.model.Student;
import com.alangomes.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByrId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Estudante com id = " + id + " não encontrado."));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student modifyStudent(Long id, Student studentDetails) {
        Student student = getStudentByrId(id);
        student.setName(studentDetails.getName());
        student.setRegister(studentDetails.getRegister());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    public void removeStudent(Long id) {
        getStudentByrId(id); // Lança exceção se não for encontrado
        studentRepository.deleteById(id);
    }
}