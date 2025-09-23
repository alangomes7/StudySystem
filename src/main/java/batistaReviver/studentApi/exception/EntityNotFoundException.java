package batistaReviver.studentApi.exception;

/**
 * Custom exception thrown when an entity is not found in the database.
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     * @param msg the detail message.
     */
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
