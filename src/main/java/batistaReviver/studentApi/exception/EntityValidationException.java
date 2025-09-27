package batistaReviver.studentApi.exception;

/** Custom exception thrown when an attempt is made to create an entity that already exists. */
public class EntityValidationException extends RuntimeException {

  /**
   * Constructs a new EntityAlreadyExistsException with the specified detail message.
   *
   * @param msg the detail message.
   */
  public EntityValidationException(String msg) {
    super(msg);
  }
}
