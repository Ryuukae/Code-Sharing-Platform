package platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CodeSnippetApiResponse {
	private final String code;

	@JsonProperty("date")
	private final LocalDateTime timeStamp;

	public CodeSnippetApiResponse(String code, LocalDateTime timeStamp) {
		this.code = code;
		this.timeStamp = timeStamp;
	}

	public String getCode() {
		return this.code;
	}

	public LocalDateTime getTimeStamp() {
		return this.timeStamp;
	}
}
