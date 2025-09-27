package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityAlreadyExistsException;
import batistaReviver.studentApi.exception.EntityNotFoundException;
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

@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final StudentRepository studentRepository;
  private final StudyClassRepository studyClassRepository;

  /**
   * Retrieves all subscriptions.
   *
   * @return A list of all {@link Subscription} entities.
   */
  public List<Subscription> getAllSubscriptions() {
    return subscriptionRepository.findAll();
  }

  /**
   * Creates a new subscription, enrolling a student in a class.
   *
   * @param studentId The ID of the student subscribing.
   * @param studyClassId The ID of the class to subscribe to.
   * @return The newly created Subscription entity.
   * @throws EntityNotFoundException if the student or class with the given IDs are not found.
   * @throws EntityAlreadyExistsException if the student is already subscribed to the class.
   */
  @Transactional
  public Subscription createSubscription(Long studentId, Long studyClassId) {
    // Prevent duplicate subscriptions
    if (subscriptionRepository.existsByStudentIdAndStudyClassId(studentId, studyClassId)) {
      throw new EntityAlreadyExistsException("Student is already subscribed to this class.");
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
    return subscriptionRepository.save(newSubscription);
  }

  /**
   * Deletes a subscription by its ID. This action does not affect the related Student or StudyClass
   * entities.
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
   * @return A list of {@link Subscription} entities for the student.
   * @throws EntityNotFoundException if no student is found with the given ID.
   */
  public List<Subscription> getStudentHistory(Long studentId) {
    // 1. Check if the student exists to fulfill the Javadoc contract.
    if (!studentRepository.existsById(studentId)) {
      throw new EntityNotFoundException("Student with id = " + studentId + " not found.");
    }
    // 2. Use the efficient repository method instead of iterating over all subscriptions.
    return subscriptionRepository.findByStudentId(studentId);
  }

  /**
   * Retrieves all subscriptions for a specific class.
   *
   * @param studyClassId The ID of the class.
   * @return A list of {@link Subscription} entities for the class.
   * @throws EntityNotFoundException if no class is found with the given ID.
   */
  public List<Subscription> getSubscriptionsByClass(Long studyClassId) {
    // Check if the class exists before querying for its subscriptions.
    if (!studyClassRepository.existsById(studyClassId)) {
      throw new EntityNotFoundException("StudyClass with id = " + studyClassId + " not found.");
    }
    // Use the custom repository method to efficiently find all subscriptions.
    return subscriptionRepository.findByStudyClassId(studyClassId);
  }
}
