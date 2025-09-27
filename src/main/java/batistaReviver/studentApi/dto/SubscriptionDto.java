package batistaReviver.studentApi.dto;

import batistaReviver.studentApi.model.Subscription;
import java.time.LocalDateTime;

/**
 * DTO for representing a {@link Subscription}. This object is used to transfer subscription data
 * between the service and the client, avoiding issues with lazy loading.
 *
 * @param id The subscription ID.
 * @param date The date of the subscription.
 * @param studentId The ID of the subscribed student.
 * @param studentName The name of the subscribed student.
 * @param studyClassId The ID of the class.
 * @param classCode The unique code of the class.
 */
public record SubscriptionDto(
    Long id,
    LocalDateTime date,
    Long studentId,
    String studentName,
    Long studyClassId,
    String classCode) {
  public SubscriptionDto(Subscription subscription) {
    this(
        subscription.getId(),
        subscription.getDate(),
        subscription.getStudent().getId(),
        subscription.getStudent().getName(),
        subscription.getStudyClass().getId(),
        subscription.getStudyClass().getClassCode());
  }
}
