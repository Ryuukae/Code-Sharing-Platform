package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.util.DateUtils;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private static AtomicInteger lastId = new AtomicInteger(0);

	private String codeSnippet;

	@JsonIgnore
	private int id;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private String timestamp;

	public CodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
		this.id = lastId.incrementAndGet();
		this.timestamp = DateUtils.formatDate(LocalDateTime.now()); // Use DateUtils to format the date
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
		this.timestamp = DateUtils.formatDate(LocalDateTime.now()); // Use DateUtils to format the date
	}

	public String getTimestamp() {
		logger.debug("Getting timestamp: {}", this.timestamp);
		return timestamp;
	}
}
