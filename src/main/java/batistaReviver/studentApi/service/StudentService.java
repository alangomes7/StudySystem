package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Student;
import batistaReviver.studentApi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing student-related business logic.
 * It interacts with the {@link StudentRepository} to perform CRUD operations.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Retrieves all students.
     * @return a list of all {@link Student} entities.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their ID.
     * @param id The ID of the student to retrieve.
     * @return The {@link Student} entity if found.
     * @throws EntityNotFoundException if no student is found with the given ID.
     */
    public Student getStudentByrId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Estudante com id = " + id + " não encontrado."));
    }

    /**
     * Adds a new student to the database.
     * @param student The {@link Student} entity to add.
     * @return The saved {@link Student} entity with the generated ID.
     */
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Modifies the details of an existing student.
     * This operation is transactional.
     * @param id The ID of the student to modify.
     * @param studentDetails A {@link Student} object containing the new details.
     * @return The updated {@link Student} entity.
     * @throws EntityNotFoundException if no student is found with the given ID.
     */
    @Transactional
    public Student modifyStudent(Long id, Student studentDetails) {
        Student student = getStudentByrId(id);
        student.setName(studentDetails.getName());
        student.setRegister(studentDetails.getRegister());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    /**
     * Removes a student from the database by their ID.
     * It first checks if the student exists before attempting to delete.
     * @param id The ID of the student to remove.
     * @throws EntityNotFoundException if no student is found with the given ID.
     */
    public void removeStudent(Long id) {
        getStudentByrId(id); // Lança exceção se não for encontrado
        studentRepository.deleteById(id);
    }
}