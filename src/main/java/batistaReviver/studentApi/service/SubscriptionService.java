package batistaReviver.studentApi.service;

import batistaReviver.studentApi.dto.SubscriptionDto;
import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.exception.StudentEnrolledException;
import batistaReviver.studentApi.model.Student;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.model.Subscription;
import batistaReviver.studentApi.repository.StudentRepository;
import batistaReviver.studentApi.repository.StudyClassRepository;
import batistaReviver.studentApi.repository.SubscriptionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing subscription-related business logic.
 *
 * <p>This class handles the creation, retrieval, and deletion of {@link Subscription} entities,
 * which link students to study classes.
 */
@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final StudentRepository studentRepository;
  private final StudyClassRepository studyClassRepository;

  /**
   * Retrieves all subscriptions.
   *
   * @return A list of all {@link SubscriptionDto}s.
   */
  @Transactional(readOnly = true)
  public List<SubscriptionDto> getAllSubscriptions() {
    return subscriptionRepository.findAll().stream().map(SubscriptionDto::new).toList();
  }

  /**
   * Creates a new subscription, enrolling a student in a class.
   *
   * @param studentId The ID of the student subscribing.
   * @param studyClassId The ID of the class to subscribe to.
   * @return The newly created {@link SubscriptionDto}.
   * @throws EntityNotFoundException if the student or class with the given IDs are not found.
   * @throws StudentEnrolledException if the student is already subscribed to the class.
   */
  @Transactional
  public SubscriptionDto createSubscription(Long studentId, Long studyClassId) {
    // Prevent duplicate subscriptions
    if (subscriptionRepository.existsByStudentIdAndStudyClassId(studentId, studyClassId)) {
      throw new StudentEnrolledException("Student is already subscribed to this class.");
    }

    Student student =
        studentRepository
            .findById(studentId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException("Student with id = " + studentId + " not found."));

    StudyClass studyClass =
        studyClassRepository
            .findById(studyClassId)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "StudyClass with id = " + studyClassId + " not found."));

    Subscription newSubscription = new Subscription(student, studyClass);
    Subscription savedSubscription = subscriptionRepository.save(newSubscription);
    return new SubscriptionDto(savedSubscription);
  }

  /**
   * Deletes a subscription by its ID. This action does not affect the related {@link Student} or
   * {@link StudyClass} entities.
   *
   * @param id The ID of the subscription to delete.
   * @throws EntityNotFoundException if no subscription with the given ID is found.
   */
  @Transactional
  public void deleteSubscription(Long id) {
    if (!subscriptionRepository.existsById(id)) {
      throw new EntityNotFoundException("Subscription with id = " + id + " not found.");
    }
    subscriptionRepository.deleteById(id);
  }

  /**
   * Retrieves the subscription history for a specific student.
   *
   * @param studentId The ID of the student.
   * @return A list of {@link SubscriptionDto}s for the student.
   * @throws EntityNotFoundException if no student is found with the given ID.
   */
  @Transactional(readOnly = true)
  public List<SubscriptionDto> getStudentHistory(Long studentId) {
    if (!studentRepository.existsById(studentId)) {
      throw new EntityNotFoundException("Student with id = " + studentId + " not found.");
    }
    return subscriptionRepository.findByStudentId(studentId).stream()
        .map(SubscriptionDto::new)
        .toList();
  }

  /**
   * Retrieves all subscriptions for a specific class.
   *
   * @param studyClassId The ID of the class.
   * @return A list of {@link SubscriptionDto}s for the class.
   * @throws EntityNotFoundException if no class is found with the given ID.
   */
  @Transactional(readOnly = true)
  public List<SubscriptionDto> getSubscriptionsByClass(Long studyClassId) {
    if (!studyClassRepository.existsById(studyClassId)) {
      throw new EntityNotFoundException("StudyClass with id = " + studyClassId + " not found.");
    }
    return subscriptionRepository.findByStudyClassId(studyClassId).stream()
        .map(SubscriptionDto::new)
        .toList();
  }
}
