package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an entity fails validation, such as when creating a resource that would
 * conflict with an existing one (e.g., due to a duplicate unique field).
 *
 * <p>This is an unchecked exception. The {@link ResponseStatus} annotation ensures that this
 * exception results in an HTTP 409 Conflict response, which is a standard way to handle such cases
 * in REST APIs.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityValidationException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /** Constructs a new {@code EntityValidationException} with the specified detail message. */
  public EntityValidationException(String message) {
    super(message);
  }
}
