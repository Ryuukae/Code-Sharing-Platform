package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private static int lastId = 0; // added static int
	private String codeSnippet;

	@JsonIgnore
	private int id;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;

	public CodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
		this.id = ++lastId; // use incremented lastId as id
		this.timestamp = LocalDateTime.now();
	}


	public int getId() {
		logger.debug("Getting ID: {}", this.id);
		return this.id;
	}

	public void setId(int id) {
		logger.debug("Setting ID: {}", id);
		this.id = id;
	}

	public String getCode() {
		logger.debug("Getting code snippet: {}", this.codeSnippet);
		return this.codeSnippet;
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
