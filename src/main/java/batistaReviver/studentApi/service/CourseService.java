package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Course;
import batistaReviver.studentApi.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling business logic related to {@link Course} entities.
 *
 * <p>This class provides methods for creating, retrieving, updating, and deleting courses, acting
 * as an intermediary between the controller and the repository.
 */
@Service
public class CourseService {

  private final CourseRepository courseRepository;

  /**
   * Constructs a new CourseService with the specified repository.
   *
   * @param courseRepository The repository used for course data access.
   */
  @Autowired
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  /**
   * Retrieves all courses from the database.
   *
   * @return A list of all {@link Course} entities.
   */
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  /**
   * Retrieves a single course by its unique identifier.
   *
   * @param id The ID of the course to retrieve.
   * @return The {@link Course} entity with the specified ID.
   * @throws EntityNotFoundException if no course with the given ID is found.
   */
  public Course getCourseById(Long id) {
    return courseRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
  }

  /**
   * Creates and persists a new course.
   *
   * @param course The {@link Course} entity to create.
   * @return The newly created and saved {@link Course} entity.
   */
  public Course createCourse(Course course) {
    // Optional: Add validation logic here, e.g., check for duplicate names
    return courseRepository.save(course);
  }

  /**
   * Updates an existing course's information.
   *
   * @param id The ID of the course to update.
   * @param courseDetails An object containing the new details for the course.
   * @return The updated {@link Course} entity.
   * @throws EntityNotFoundException if no course with the given ID is found.
   */
  public Course updateCourse(Long id, Course courseDetails) {
    Course existingCourse = getCourseById(id);

    existingCourse.setName(courseDetails.getName());
    existingCourse.setDescription(courseDetails.getDescription());
    // The 'studyClasses' set is managed via its own endpoints or logic, not typically updated here.

    return courseRepository.save(existingCourse);
  }

  /**
   * Deletes a course by its unique identifier.
   *
   * @param id The ID of the course to delete.
   * @throws EntityNotFoundException if no course with the given ID is found.
   */
  public void deleteCourse(Long id) {
    Course courseToDelete = getCourseById(id);
    courseRepository.delete(courseToDelete);
  }
}
