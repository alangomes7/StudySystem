package batistaReviver.studentApi.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a specific entity is not found in the database.
 *
 * <p>This is an unchecked exception because it typically signifies a client error, such as
 * providing an invalid ID for an entity that does not exist. The {@link ResponseStatus} annotation
 * ensures that this exception results in an HTTP 404 Not Found response, which is a standard way to
 * handle such cases in REST APIs.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new {@code EntityNotFoundException} with the specified detail message.
   *
   * @param message The detail message.
   */
  public EntityNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructs a new {@code EntityNotFoundException} with the specified detail message and cause.
   *
   * <p>This is useful for wrapping lower-level exceptions while providing context-specific
   * information.
   *
   * @param message The detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
   */
  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
