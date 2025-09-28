package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation cannot be performed on a student because they are enrolled in
 * one or more classes. For example, this prevents the deletion of a student who has active
 * subscriptions.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class StudentEnrolledException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new StudentEnrolledException with the specified detail message.
   *
   * @param message the detail message.
   */
  public StudentEnrolledException(String message) {
    super(message);
  }
}
