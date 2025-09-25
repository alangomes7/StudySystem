package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.Course;
import batistaReviver.studentApi.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Course} entities.
 *
 * <p>Provides API endpoints for performing CRUD operations on courses. All endpoints are
 * prefixed with "/api/v1/courses".
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    /**
     * Constructs a new CourseController with the specified service.
     *
     * @param courseService The service used to manage course business logic.
     */
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Handles HTTP GET requests to retrieve all courses.
     *
     * @return A {@link ResponseEntity} containing a list of all courses and an OK status.
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /**
     * Handles HTTP GET requests to retrieve a single course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return A {@link ResponseEntity} containing the found course and an OK status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    /**
     * Handles HTTP POST requests to create a new course.
     *
     * @param course The course object from the request body.
     * @return A {@link ResponseEntity} containing the newly created course and a CREATED status.
     */
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    /**
     * Handles HTTP PUT requests to update an existing course.
     *
     * @param id The ID of the course to update.
     * @param courseDetails The course object with updated details from the request body.
     * @return A {@link ResponseEntity} containing the updated course and an OK status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(id, courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    /**
     * Handles HTTP DELETE requests to remove a course.
     *
     * @param id The ID of the course to delete.
     * @return A {@link ResponseEntity} with NO_CONTENT status indicating successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}