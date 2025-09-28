package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation cannot be performed due to the presence of existing
 * subscriptions. For example, this prevents the deletion of a study class that still has students
 * enrolled.
 *
 * <p>This is an unchecked exception. When thrown, it is caught by the {@link
 * GlobalExceptionHandler}, which returns an HTTP 409 Conflict response, a standard way to handle
 * such cases in REST APIs.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionFoundException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /** Constructs a new {@code SubscriptionFoundException} with the specified detail message. */
  public SubscriptionFoundException(String message) {
    super(message);
  }
}
