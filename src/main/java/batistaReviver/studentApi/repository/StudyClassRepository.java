package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.StudyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link StudyClass} entity.
 *
 * <p>This interface allows for CRUD operations and other data access patterns for StudyClass
 * entities without requiring manual implementation.
 */
@Repository
public interface StudyClassRepository extends JpaRepository<StudyClass, Long> {}
