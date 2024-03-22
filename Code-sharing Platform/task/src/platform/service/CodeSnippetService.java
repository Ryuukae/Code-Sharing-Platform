package platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

@Service
public class CodeSnippetService {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetService.class);

	private final CodeSnippet codeSnippet = new CodeSnippet("""
			public static void main(String[] args) { SpringApplication.run(CodeSharingPlatform.class, args); }
			"""
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
		logger.debug("Updating the code snippet with newCode: {}", newCode);
		try {
			codeSnippet.setCode(newCode);
			logger.debug("Updated the code snippet successfully");
		} catch (Exception e) {
			logger.error("Error while updating the code snippet", e);
			throw e;
		}
		logger.debug("CodeSnippetService: updateCodeSnippet method end");
	}
}
