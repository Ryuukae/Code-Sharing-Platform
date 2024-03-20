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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultExceptionHandler(Exception ex, HttpServletRequest request) {
		log.error("Global Exception caught: ", ex);
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerExceptions(NullPointerException ex, HttpServletRequest request) {
		log.error("Null Pointer Exception occurred: ", ex);
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentExceptions(IllegalArgumentException ex, HttpServletRequest request) {
		log.error("Illegal Argument Exception occurred: ", ex);
		return buildResponseEntity(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex, HttpServletRequest request) {
		log.error("Runtime Exception occurred: ", ex);
		return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> buildResponseEntity(Exception ex, HttpStatus status) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("exceptionType", ex.getClass().getName());

		log.debug("Error Details: ");
		log.debug("timestamp: {}", LocalDateTime.now());
		log.debug("message: {}", ex.getMessage());
		log.debug("exceptionType: {}", ex.getClass().getName());

		return new ResponseEntity<>(body, status);
	}
}
