package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private String codeSnippet;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;

	public CodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
		this.timestamp = LocalDateTime.now();
	}

	public String getCode() {
		logger.debug("Getting code snippet: {}", this.codeSnippet);
		return codeSnippet;
	}

	public void setCode(String codeSnippet) {
		logger.debug("Setting code snippet: {}", codeSnippet);
		this.codeSnippet = codeSnippet;
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		logger.debug("Getting timestamp: {}", this.timestamp);
		return timestamp;
	}
}
