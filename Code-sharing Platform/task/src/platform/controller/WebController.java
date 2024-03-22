package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

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
		logger.debug("Entering displayCodeSnippet() method");
		CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();
		logger.debug("Code snippet retrieved: {}", codeSnippet);
		String html =
				"<html><head><title>Code</title></head><body><pre id=\"code_snippet\">" + codeSnippet.getCode() + "</pre><span " +
						"id=\"load_date\">" + codeSnippet.getTimestamp() + "</span></body></html>";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);
		logger.debug("Setting the response headers");
		logger.info("Returning the code snippet as a ResponseEntity");
		return new ResponseEntity<>(html, headers, HttpStatus.OK);
	}
}
