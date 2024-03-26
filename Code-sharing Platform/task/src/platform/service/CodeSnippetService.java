package platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

@Service
public class CodeSnippetService {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetService.class);

	private final CodeSnippet codeSnippet = new CodeSnippet("""
			public static void main(String[] args) { SpringApplication.run(CodeSharingPlatform.class, args); }"""
	);

	public CodeSnippet getCodeSnippet() {
		logger.debug("Getting the code snippet");
		try {
			return codeSnippet;
		} catch (Exception e) {
			logger.error("Error while getting the code snippet", e);
			throw e;
		}
	}

	public void updateCodeSnippet(String newCode) {
		logger.debug("CodeSnippetService: updateCodeSnippet method start");
		logger.debug("Updating code snippet with: {}", newCode);
		try {
			// Remove the leading and trailing double quotes if present
			if (newCode.startsWith("\"") && newCode.endsWith("\"")) {
				newCode = newCode.substring(1, newCode.length() - 1);
			}
			codeSnippet.setCode(newCode);
			logger.info("Code snippet updated successfully");
		} catch (Exception e) {
			logger.error("Error while updating the code snippet", e);
			throw e;
		}
		logger.debug("CodeSnippetService: updateCodeSnippet method end");
	}
}
