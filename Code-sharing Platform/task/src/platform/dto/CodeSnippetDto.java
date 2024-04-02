package platform.dto;

public class CodeSnippetDto {
	private String code;
	private String timestamp;

	// generate constructor, getters, and setters
	public CodeSnippetDto(String code, String timestamp) {
		this.code = code;
		this.timestamp = timestamp;
	}

	public String getCode() {
		return code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFormattedTimestamp(String formattedTimestamp) {
		this.timestamp = formattedTimestamp;
	}


}
