package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.Subscription;
import batistaReviver.studentApi.service.SubscriptionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  /** DTO for creating a new Subscription. */
  public record CreateSubscriptionRequest(Long studentId, Long studyClassId) {}

  /**
   * GET /subscriptions : Retrieves all subscriptions.
   *
   * <p>Can be filtered by providing a 'studentId' or 'studyClassId' query parameter.
   *
   * @param studentId (Optional) The ID of the student to filter by.
   * @param studyClassId (Optional) The ID of the class to filter by.
   * @return A response entity with a list of subscriptions and HTTP status 200 (OK).
   */
  @GetMapping
  public ResponseEntity<List<Subscription>> findSubscriptions(
      @RequestParam(required = false) Long studentId,
      @RequestParam(required = false) Long studyClassId) {

    // This single method now handles all GET cases for better flexibility.
    if (studentId != null) {
      return ResponseEntity.ok(subscriptionService.getStudentHistory(studentId));
    }

    if (studyClassId != null) {
      return ResponseEntity.ok(subscriptionService.getSubscriptionsByClass(studyClassId));
    }

    return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
  }

  /**
   * POST /subscriptions : Creates a new subscription for a student to a class.
   *
   * @param request The request body containing the student and class IDs.
   * @return A response entity with the created subscription and HTTP status 201 (Created).
   */
  @PostMapping
  public ResponseEntity<Subscription> createSubscription(
      @RequestBody CreateSubscriptionRequest request) {
    Subscription createdSubscription =
        subscriptionService.createSubscription(request.studentId(), request.studyClassId());
    return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
  }

  /**
   * DELETE /subscriptions/{id} : Deletes a subscription by its ID.
   *
   * @param id The ID of the subscription to delete.
   * @return A response entity with no content and HTTP status 204 (No Content).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
    subscriptionService.deleteSubscription(id);
    return ResponseEntity.noContent().build();
  }
}
