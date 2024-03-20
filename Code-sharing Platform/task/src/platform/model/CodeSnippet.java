package platform.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private String codeSnippet;
	private LocalDateTime timestamp;

	public CodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
		this.timestamp = LocalDateTime.now();
		logger.debug("CodeSnippet object is created with initial code: {}", codeSnippet);
	}

	public String getCode() {
		logger.debug("getCode() is called. Returning code: {}", codeSnippet);
		return codeSnippet;
	}

	public void setCode(String code) {
		this.codeSnippet = code;
		this.timestamp = LocalDateTime.now();
		logger.debug("setCode() is called. Code is updated to: {}", code);
	}

	public LocalDateTime getTimestamp() {
		logger.debug("getTimestamp() is called. Returning timestamp: {}", timestamp);
		return timestamp;
	}
}
