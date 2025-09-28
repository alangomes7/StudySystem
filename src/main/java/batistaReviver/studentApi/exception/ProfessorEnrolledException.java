package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation cannot be performed on a professor because they are assigned
 * to one or more study classes. For example, this prevents the deletion of a professor who is
 * actively teaching.
 *
 * <p>This is an unchecked exception. The {@link ResponseStatus} annotation ensures that this
 * exception results in an HTTP 409 Conflict response, which is a standard way to handle such cases
 * in REST APIs.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProfessorEnrolledException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new {@code ProfessorEnrolledException} with the specified detail message.
   *
   * @param message The detail message.
   */
  public ProfessorEnrolledException(String message) {
    super(message);
  }
}
