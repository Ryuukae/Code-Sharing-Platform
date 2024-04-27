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
	// logging utility
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);

	// unique id generation
	private static AtomicInteger lastId = new AtomicInteger(0);

	// code snippet content
	@JsonProperty("code")
	@Column(name = "code")
	private String codeSnippet;

	// unique id
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// date of the code snippet creation
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	@Column(name = "timestamp")
	private String timestamp;

	protected CodeSnippet() {
		logger.info("CodeSnippet default constructor called");
		id = 0;
		codeSnippet = null;
		timestamp = null;
		logger.info("CodeSnippet default constructor exit");
	}

	// constructor utilizing a provided code snippet
	public CodeSnippet(String codeSnippet) {
		logger.info("CodeSnippet constructor called");

		this.codeSnippet = codeSnippet;
		this.id = lastId.incrementAndGet();  // incrementing lastId for unique id

		// format current time for timestamp
		this.timestamp = DateUtils.formatDate(LocalDateTime.now());

		logger.info("CodeSnippet instance created with id {}", id);
		logger.info("CodeSnippet constructor exit");
	}

	// set the lastId Atomic integer
	public static void setLastId(AtomicInteger atomicInteger) {
		logger.info("Setting lastId: {}", atomicInteger);
		lastId = atomicInteger;
	}

	// get id of the CodeSnippet
	public int getId() {
		logger.info("Getting ID: {}", this.id);
		return this.id;
	}

	// get code snippet content
	public String getCode() {
		logger.info("Getting code snippet: {}", this.codeSnippet);
		return this.codeSnippet;
	}

	// get timestamp of the CodeSnippet
	public String getTimestamp() {
		logger.info("Getting timestamp: {}", this.timestamp);
		return timestamp;
	}

	// set a new timestamp
	public void setTimestamp(String timestamp) {
		logger.info("Setting timestamp: {}", timestamp);

		this.timestamp = timestamp;

		logger.info("Timestamp set to {}", this.timestamp);
	}

	// helper class for date formatting
	static class DateUtils {
		// format date to desired format
		public static String formatDate(LocalDateTime date) {
			return date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
		}
	}
}
