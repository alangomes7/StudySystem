package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Course} entity.
 *
 * <p>This interface provides the mechanism for storage, retrieval, and search behavior for Course
 * objects. Spring will automatically provide the implementation at runtime.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {}
