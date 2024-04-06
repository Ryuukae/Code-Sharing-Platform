package platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeSnippetDto {

	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetDto.class);

	@JsonProperty("code")
	private String code;
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private String timestamp;

	// generate constructor, getters, and setters
	public CodeSnippetDto(String code, String timestamp) {
		this.code = code;
		this.timestamp = timestamp;

		logger.debug("CodeSnippetDto object created with code: {} and timestamp: {}", code, timestamp);
	}

	public String getCode() {
		logger.debug("getCode() called, returning: {}", code);
		return code;
	}

	public String getTimestamp() {
		logger.debug("getTimestamp() called, returning: {}", timestamp);
		return timestamp;
	}
}
