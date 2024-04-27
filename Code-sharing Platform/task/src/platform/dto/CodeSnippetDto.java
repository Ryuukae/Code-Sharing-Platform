package platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeSnippetDto {

	// Create a Logger instance for this class
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetDto.class);

	// JSON property "code"
	@JsonProperty("code")
	private String code;

	// JSON property "date" with a custom "MM-dd-yyyy HH:mm:ss" pattern format
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private String timestamp;

	// Constructor
	public CodeSnippetDto(String code, String timestamp) {
		// Assign values
		this.code = code;
		this.timestamp = timestamp;

		// Log a debug message indicating that the constructor has run successfully with the given parameters
		logger.debug("Enter: CodeSnippetDto constructor. Parameters: code = {}, timestamp = {}", code, timestamp);
		logger.debug("Exit: CodeSnippetDto constructor");
	}

	// Getter for "code" field with debug logging
	public String getCode() {
		// Log entry to method
		logger.debug("Enter: getCode()");

		String toReturn = this.code;

		// Log what we are returning and exit the method
		logger.debug("Exit: getCode(), returning: {}", toReturn);

		return toReturn;
	}

	// Getter for "timestamp" field with debug logging
	public String getTimestamp() {
		// Log entry to method
		logger.debug("Enter: getTimestamp()");

		String toReturn = this.timestamp;

		// Log what we are returning and exit the method
		logger.debug("Exit: getTimestamp(), returning: {}", toReturn);

		return toReturn;
	}
}
