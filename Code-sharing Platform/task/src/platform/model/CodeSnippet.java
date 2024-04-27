package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "code_snippets")
public class CodeSnippet {
	// logging utility
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippet.class);


	// unique id
	@Id
	@JsonIgnore
	@Column(name = "id", length = 36, unique = true, nullable = false, updatable = false)
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id;


	@JsonProperty("code")
	@Column(name = "code", nullable = false, length = 5000)
	private String codeSnippet;

	// date of the code snippet creation
	@Column(name = "timestamp", nullable = false)
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private String timestamp;

	@Column(name = "time", nullable = false)
	@JsonProperty("time")
	private int time;

	@Column(name = "views", nullable = false)
	@JsonProperty("views")
	private int views;


	public CodeSnippet() {
		logger.info("CodeSnippet default constructor called");
		logger.info("CodeSnippet default constructor exit");
	}

	public CodeSnippet(String newCode, int time, int views) {
		logger.info("CodeSnippet constructor called...");
		this.id = UUID.randomUUID();
		this.codeSnippet = newCode;
		this.timestamp = DateUtils.formatDate(LocalDateTime.now());
		this.time = time;
		this.views = views;

		logger.info("CodeSnippet instance created with the following properties:\nId: {},\nCodeSnippet: {},\nTimestamp: {},\nTime: {}," +
				            "\nViews: {}", this.id, this.codeSnippet, this.timestamp, this.time, this.views);
		logger.info("CodeSnippet constructor exit.");
	}


	// get id of the CodeSnippet
	public UUID getId() {
		logger.info("Getting ID...:");
		return this.id;
	}

	// get code snippet content
	public String getCode() {
		logger.info("Getting code snippet...:");
		return this.codeSnippet;
	}

	// get timestamp of the CodeSnippet
	public String getTimestamp() {
		logger.info("Getting timestamp...");
		return timestamp;
	}

	// set a new timestamp
	public void setTimestamp(String timestamp) {
		logger.info("Setting timestamp...", timestamp);

		this.timestamp = timestamp;

	}

	public void setViews(int i) {
		logger.info("Setting views:...");
		this.views = i;
	}

	public int getViews() {
		logger.info("Getting views...");
		return this.views;
	}


	public void setTime(int i) {
		logger.info("Setting time...");
		this.time = i;
	}

	public int getTime() {
		logger.info("Getting time...", this.time);
		return this.time;

	}

	// helper class for date formatting
	static class DateUtils {
		// format date to desired format
		public static String formatDate(LocalDateTime date) {
			logger.info("Formatting date...");
			String formattedDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
			logger.info("Date Formatted to {}", formattedDate);
			return formattedDate;

		}
	}
}
