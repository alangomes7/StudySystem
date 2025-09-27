package batistaReviver.studentApi.exception;

/**
 * Custom exception thrown when an attempt is made to create an entity that already exists,
 * violating a uniqueness constraint. For example, trying to subscribe a student to a class they are
 * already enrolled in.
 */
public class EntityAlreadyExistsException extends RuntimeException {

  /**
   * Constructs a new EntityAlreadyExistsException with the specified detail message.
   *
   * @param msg the detail message.
   */
  public EntityAlreadyExistsException(String msg) {
    super(msg);
  }
}
