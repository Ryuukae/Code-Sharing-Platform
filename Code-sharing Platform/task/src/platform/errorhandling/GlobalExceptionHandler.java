package platform.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

// This is a global exception handler that deals with different types of exceptions that might occur in the application
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	// Logger instance using slf4j
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	// This method handles all exceptions not specifically dealt with by other exception handlers in this class
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultExceptionHandler(Exception ex, HttpServletRequest request) {
		// Logging the exception details at ERROR level
		log.error("Global Exception caught: ", ex);
		// Build the response entity
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// This method handles NullPointerExceptions
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerExceptions(NullPointerException ex, HttpServletRequest request) {
		// Logging the exception details at ERROR level
		log.error("Null Pointer Exception occurred: ", ex);
		// Build the response entity
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// This method handles IllegalArgumentExceptions
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentExceptions(IllegalArgumentException ex, HttpServletRequest request) {
		// Logging the exception details at ERROR level
		log.error("Illegal Argument Exception occurred: ", ex);
		// Build the response entity
		return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
	}

	// This method handles RuntimeExceptions
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex, HttpServletRequest request) {
		// Logging the exception details at ERROR level
		log.error("Runtime Exception occurred: ", ex);
		// Build the response entity
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Method to build response entity
	private ResponseEntity<Object> buildResponseEntity(Exception ex, HttpStatus status) {
		log.debug("Entering buildResponseEntity method...");
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("exceptionType", ex.getClass().getName());

		// Enhanced debug information
		log.debug("Error Details: ");
		log.debug("timestamp: {}", body.get("timestamp"));
		log.debug("message: {}", body.get("message"));
		log.debug("exceptionType: {}", body.get("exceptionType"));
		log.debug("HTTP Status: {}", status);

		log.debug("Exiting buildResponseEntity method...");
		return new ResponseEntity<>(body, status);
	}
}
