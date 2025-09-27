package batistaReviver.studentApi.exception;

import java.io.Serial;

/**
 * Custom exception thrown when a requested entity cannot be found in the persistence layer.
 *
 * <p>This is an unchecked exception because it often indicates a client error, such as requesting
 * an entity with an ID that does not exist.
 */
public class EntityNotFoundException extends RuntimeException {

  /** A unique identifier for this serializable class. */
  @Serial private static final long serialVersionUID = 1L;

  /**
   * Constructs a new {@code EntityNotFoundException} with the specified detail message.
   *
   * @param message The detail message, which is saved for later retrieval by the {@link
   *     #getMessage()} method.
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
