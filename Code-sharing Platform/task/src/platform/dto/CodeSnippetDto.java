package platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CodeSnippetDto {

	// Create a Logger instance for this class
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetDto.class);

	// unique id

	@JsonIgnore
	private UUID id;


	// code snippet content
	@JsonProperty("codeSnippet")
	private String codeSnippet;

	// date of the code snippet creation
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private String timestamp;

	@JsonProperty("time")
	private int time;

	@JsonProperty("views")
	private int views;

	// Constructor
	public CodeSnippetDto(UUID id, String code, String timestamp, int time, int views) {
		logger.info("Enter: CodeSnippetDto constructor...");
		// Assign values
		this.id = id;
		this.codeSnippet = code;
		this.timestamp = timestamp;
		this.time = time;
		this.views = views;

		// Log a debug message indicating that the constructor has run successfully with the given parameters
		logger.info("CodeSnippetDTO created with the following parameters:\nId: {},\nCodeSnippet: {},\nTimestamp: {},\nTime: {},\nViews: " +
				            "{}", id, code, timestamp, time, views);

		logger.info("Exit: CodeSnippetDto constructor");
	}

	// Getter for "code" field with debug logging
	public String getCodeSnippet() {
		// Log entry to method
		logger.debug("Enter: getCode()");

		String toReturn = this.codeSnippet;

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

	public UUID getId() {
		return this.id;
	}

	public int getTime() {
		return this.time;
	}

	public int getViews() {
		return this.views;
	}

}
