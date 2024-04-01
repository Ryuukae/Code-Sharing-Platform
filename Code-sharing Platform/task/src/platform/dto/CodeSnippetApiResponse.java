package platform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeSnippetApiResponse {
	private final String code;

	@JsonProperty("date")
	private final String timeStamp;

	public CodeSnippetApiResponse(String code, String timeStamp) {
		this.code = code;
		this.timeStamp = timeStamp;
	}

	public String getCode() {
		return this.code;
	}

	public String getTimeStamp() {
		return this.timeStamp;
	}
}
