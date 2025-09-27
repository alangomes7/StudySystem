package batistaReviver.studentApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation cannot be completed because one or more subscriptions exist.
 * For example, attempting to delete a study class that still has students enrolled.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionFoundException extends RuntimeException {

  /**
   * Constructs a new SubscriptionFoundException with the specified detail message.
   *
   * @param message the detail message.
   */
  public SubscriptionFoundException(String message) {
    super(message);
  }
}
