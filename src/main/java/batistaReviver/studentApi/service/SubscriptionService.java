package batistaReviver.studentApi.service;

import batistaReviver.studentApi.exception.EntityNotFoundException;
import batistaReviver.studentApi.model.Student;
import batistaReviver.studentApi.model.StudyClass;
import batistaReviver.studentApi.model.Subscription;
import batistaReviver.studentApi.repository.StudentRepository;
import batistaReviver.studentApi.repository.StudyClassRepository;
import batistaReviver.studentApi.repository.SubscriptionRepository;
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
   * Creates a new subscription, enrolling a student in a class.
   *
   * @param studentId The ID of the student subscribing.
   * @param studyClassId The ID of the class to subscribe to.
   * @return The newly created Subscription entity.
   * @throws EntityNotFoundException if the student or class with the given IDs are not found.
   * @throws IllegalStateException if the student is already subscribed to the class.
   */
  @Transactional
  public Subscription createSubscription(Long studentId, Long studyClassId) {
    // Prevent duplicate subscriptions
    if (subscriptionRepository.existsByStudentIdAndStudyClassId(studentId, studyClassId)) {
      throw new IllegalStateException("Student is already subscribed to this class.");
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
}
