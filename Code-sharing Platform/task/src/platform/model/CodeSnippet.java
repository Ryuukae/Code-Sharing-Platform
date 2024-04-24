package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "code_snippets")
public class CodeSnippet {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	private static AtomicInteger lastId = new AtomicInteger(0);

	@JsonProperty("code")
	@Column(name = "code")
	private String codeSnippet;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	@Column(name = "timestamp")
	private String timestamp;

	protected CodeSnippet() {
		id = 0;
		codeSnippet = null;
		timestamp = null;
	}

	public CodeSnippet(String codeSnippet) {
		logger.debug("Creating new CodeSnippet instance");
		this.codeSnippet = codeSnippet;
		this.id = lastId.incrementAndGet();

		this.timestamp = DateUtils.formatDate(LocalDateTime.now()); // Use DateUtils to format the date
		logger.debug("CodeSnippet instance created with id {}, code snippet {}, timestamp {}", id, codeSnippet, timestamp);
	}

	public static void setLastId(AtomicInteger atomicInteger) {
		lastId = atomicInteger;
	}


	public int getId() {
		logger.debug("Getting ID: {}", this.id);
		return this.id;
	}


	public String getCode() {
		logger.debug("Getting code snippet: {}", this.codeSnippet);
		return this.codeSnippet;
	}


	public String getTimestamp() {
		logger.debug("Getting timestamp: {}", this.timestamp);
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		logger.debug("Setting timestamp: {}", timestamp);
		this.timestamp = timestamp;
		logger.debug("Timestamp set to {}", this.timestamp);
	}

	static class DateUtils {
		public static String formatDate(LocalDateTime date) {
			return date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
		}
	}
}
