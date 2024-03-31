package platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeSnippetApiResponse {
	private final String code;

	@JsonProperty("date")
	private final String timestamp;

	public CodeSnippetApiResponse(String code, String timestamp) {
		this.code = code;
		this.timestamp = timestamp;
	}

	public String getCode() {
		return this.code;
	}

	public String getTimeStamp() {
		return this.timestamp;
	}
}
