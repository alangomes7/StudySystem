package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.Student;
import batistaReviver.studentApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing students.
 * Provides endpoints for CRUD operations on Student entities.
 */
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Retrieves a list of all students.
     * @return a list of all students.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * Retrieves a specific student by their ID.
     * @param id The ID of the student to retrieve.
     * @return a ResponseEntity containing the student if found, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentByrId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentByrId(id));
    }

    /**
     * Adds a new student.
     * @param student The student object to be added.
     * @return a ResponseEntity containing the created student and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    /**
     * Modifies an existing student.
     * @param id The ID of the student to modify.
     * @param studentDetails The new details for the student.
     * @return a ResponseEntity containing the updated student.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> modifyStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(studentService.modifyStudent(id, studentDetails));
    }

    /**
     * Removes a student by their ID.
     * @param id The ID of the student to remove.
     * @return a ResponseEntity with HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.noContent().build();
    }
}