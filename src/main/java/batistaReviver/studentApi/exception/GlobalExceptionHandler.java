package batistaReviver.studentApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class uses {@link ControllerAdvice} to handle exceptions across the whole application
 * in one global handling component.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link EntityNotFoundException} across the entire application.
     * This method is triggered when an {@link EntityNotFoundException} is thrown.
     * @param e The {@link EntityNotFoundException} that was thrown.
     * @return a {@link ResponseEntity} with a 404 Not Found status and the exception message in the body.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
