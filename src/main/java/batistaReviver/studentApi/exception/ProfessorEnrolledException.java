package batistaReviver.studentApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation cannot be performed on a professor because they are assigned
 * to one or more classes. This can prevent actions like deleting a professor who is actively
 * teaching.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProfessorEnrolledException extends RuntimeException {

  /**
   * Constructs a new ProfessorEnrolledException with the specified detail message.
   *
   * @param message the detail message.
   */
  public ProfessorEnrolledException(String message) {
    super(message);
  }
}
