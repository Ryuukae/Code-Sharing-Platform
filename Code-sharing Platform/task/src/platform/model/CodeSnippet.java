package platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private String codeSnippet;
	private LocalDateTime date;

	public CodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
		this.date = LocalDateTime.now();
		logger.debug("CodeSnippet object is created with initial code: {}", codeSnippet);
	}

	public String getCode() {
		logger.debug("getCode() is called. Returning code: {}", codeSnippet);
		return codeSnippet;
	}

	public void setCode(String code) {
		this.codeSnippet = code;
		this.date = LocalDateTime.now();
		logger.debug("setCode() is called. Code is updated to: {}", code);
	}

	@JsonProperty("date")
	public LocalDateTime getTimestamp() {
		logger.debug("getTimestamp() is called. Returning date: {}", date);
		return date;
	}
}
