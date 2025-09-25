package batistaReviver.studentApi.controller;

import batistaReviver.studentApi.model.Subscription;
import batistaReviver.studentApi.service.SubscriptionService;
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
