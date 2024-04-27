package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This is a REST Controller Class
@RestController
public class ApiController {

	// Service for handling code snippets
	private final CodeSnippetService codeSnippetService;

	// Logger for this class
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	// Constructor-based Dependency Injection for CodeSnippetService
	@Autowired
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;
		// Info log to indicate successful Autowiring
		logger.info("CodeSnippetService has been Autowired");
	}

	// Get mapping for accessing a specific code snippet by ID
	@GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CodeSnippet> getApiCodeSnippetById(@PathVariable int id) {
		// Debug log for start of method execution
		logger.debug("Start getting CodeSnippet with id {}", id);

		// Fetching the code snippet using the service
		CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);
		if (snippet == null) {
			// Error log when the code snippet is not found
			logger.error("CodeSnippet with id {} not found", id);
			return ResponseEntity.notFound().build();
		}
		// Success log indicating the fetching of the code snippet
		logger.info("CodeSnippet with id {} has been fetched successfully. Code: {}, Date: {}",
				id, snippet.getCode(), snippet.getTimestamp());

		return ResponseEntity.ok(snippet);
	}

	// Post mapping for creating a new code snippet
	@PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> createCodeSnippet(@RequestBody Map<Object, Object> requestBody) {
		// Extracting code from request body
		String newCode = (String) requestBody.get("code");

		// Debug log indicating start of creating new code snippet
		logger.debug("Creating new CodeSnippet with code {}", newCode);

		// Creating new code snippet and saving it using the service
		CodeSnippet newSnippet = new CodeSnippet(newCode);
		codeSnippetService.saveCodeSnippet(newSnippet);

		// Preparing response with the id of the newly created snippet
		Map<String, String> response = new HashMap<>();
		response.put("id", String.valueOf(newSnippet.getId()));

		// Success log indicating the successful creation of a new code snippet
		logger.info("New CodeSnippet with id {} has been added. Code: {}, Date: {}",
				newSnippet.getId(), newSnippet.getCode(), newSnippet.getTimestamp());

		return ResponseEntity.ok(response);
	}

	// Get mapping for getting the latest code snippets
	@GetMapping("/api/code/latest")
	@ResponseBody
	public Object getApiLatestCodeSnippets() {
		// Debug log indicating start of fetching latest code snippets
		logger.debug("Fetching latest CodeSnippets via the codeSnippetService");

		// Fetching latest code snippets using the service
		List<CodeSnippet> latestSnippets = codeSnippetService.getLatest10CodeSnippets();  // updated to new method

		// Logging each fetched code snippet
		latestSnippets.forEach(snippet -> logger.info("Fetched CodeSnippet.\n Id: {}, Code: {}, Date: {}",
				snippet.getId(), snippet.getCode(), snippet.getTimestamp()));

		// Success log indicating completion of fetching latest code snippets
		logger.info("Latest CodeSnippets fetched");

		return latestSnippets;
	}
}
