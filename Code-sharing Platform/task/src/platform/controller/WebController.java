package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;
import platform.util.DateUtils;

@Controller
@RequestMapping("")
public class WebController {

	private final CodeSnippetService codeSnippetService;

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;
		logger.info("WebController initialized.");
	}

	@GetMapping("/code")
	public ResponseEntity<String> displayCodeSnippet() {
		// Log a debug message as we enter the method
		logger.debug("Entering displayCodeSnippet() method");        // Retrieve a code snippet using the codeSnippetService
		CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();        // Log a debug message with the retrieved code snippet
		logger.debug("Code snippet retrieved: {}", codeSnippet);        // Set the response headers for the content type and status code
		logger.debug("Setting the response headers");        // Return the code snippet as a ResponseEntity

		try {
			logger.debug("Returning the code snippet as a ResponseEntity");
			return ResponseEntity.ok().header("Content-Type", MediaType.TEXT_PLAIN_VALUE).body(codeSnippet.getCode() + "\n" + DateUtils.formatDate(codeSnippet.getTimestamp()));
		} catch (Exception e) {
			logger.error("Error while returning the code snippet", e);
			throw e;
		}
	}
}
