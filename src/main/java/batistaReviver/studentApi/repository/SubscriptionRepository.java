package batistaReviver.studentApi.repository;

import batistaReviver.studentApi.model.Subscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link Subscription} entity.
 *
 * <p>This interface provides methods for CRUD operations and includes custom queries for managing
 * student enrollments in classes. The implementation is automatically provided by Spring at
 * runtime.
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  /**
   * Checks if a subscription exists for a given student and class.
   *
   * <p>This method uses Spring Data JPA's query derivation to generate the query from the method
   * name, preventing duplicate enrollments.
   *
   * @param studentId The ID of the student.
   * @param studyClassId The ID of the class.
   * @return {@code true} if a subscription already exists, {@code false} otherwise.
   */
  boolean existsByStudentIdAndStudyClassId(Long studentId, Long studyClassId);

  /**
   * Finds all subscriptions for a given student ID. Spring Data JPA automatically implements this
   * method.
   *
   * @param studentId The ID of the student.
   * @return A list of subscriptions for that student.
   */
  List<Subscription> findByStudentId(Long studentId);

  /**
   * Finds all subscriptions for a given studyClass ID. Spring Data JPA automatically implements
   * this method.
   *
   * @param studyClassId The ID of the studyClass.
   * @return A list of subscriptions for that studyClass.
   */
  // FIX: Renamed from findBystudyClassIdId to findByStudyClassId
  List<Subscription> findByStudyClassId(Long studyClassId);
}
