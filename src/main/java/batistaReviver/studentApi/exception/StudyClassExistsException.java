package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an attempt is made to create a study class that already exists, typically
 * identified by a unique combination of course, year, and semester.
 *
 * <p>This is an unchecked exception. When thrown, it is caught by the {@link
 * GlobalExceptionHandler}, which returns an HTTP 409 Conflict response.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class StudyClassExistsException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new StudyClassExistsException with the specified detail message.
   *
   * @param message the detail message.
   */
  public StudyClassExistsException(String message) {
    super(message);
  }
}
