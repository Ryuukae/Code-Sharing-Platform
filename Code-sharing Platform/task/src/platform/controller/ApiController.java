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

@RestController  // This means that this class is a Controller
public class ApiController {

	private final CodeSnippetService codeSnippetService;  // CodeSnippet Service is declared
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired  // This means that Spring boot will auto wire the object as a bean
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;  // Initializing the CodeSnippetService with the autowired bean
		logger.info("CodeSnippetService has been Autowired");  // Logging this initialization info for debug purposes
	}

	@GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)  // HTTP GET mapping for the endpoint /api/code/{id}
	public ResponseEntity<CodeSnippet> getApiCodeSnippetById(@PathVariable int id) {
		logger.debug("Start getting CodeSnippet with id {}", id);  // Logging the start of the method
		CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);  // Calling the service method to get the CodeSnippet by ID
		if (snippet == null) {  // Checking if the fetched CodeSnippet is null
			logger.error("CodeSnippet with id {} not found", id);  // If null, logging the error
			return ResponseEntity.notFound().build();  // And returning HTTP 404 error
		}
		logger.info("CodeSnippet with id {} has been fetched successfully. Code: {}, Date: {}",
				id, snippet.getCode(), snippet.getTimestamp());  // If not null, logging the successful fetching of the snippet
		return ResponseEntity.ok(snippet);  // And returning OK(200) with the snippet
	}

	@PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)  // HTTP POST mapping for creating a new code snippet
	public ResponseEntity<Map<String, String>> createCodeSnippet(@RequestBody Map<Object, Object> requestBody) {
		String newCode = (String) requestBody.get("code");  // Getting the code from request body
		logger.debug("Creating new CodeSnippet with code {}", newCode);  // Logging that we're about to create a new code snippet
		CodeSnippet newSnippet = new CodeSnippet(newCode);  // Creating a new code snippet object from the received code
		codeSnippetService.addCodeSnippet(newSnippet);  // Adding the created code snippet to the database
		Map<String, String> response = new HashMap<>();  // Initializing the response map
		response.put("id", String.valueOf(newSnippet.getId()));  // Adding the created snippet's ID to the response map
		logger.info("New CodeSnippet with id {} has been added. Code: {}, Date: {}",
				newSnippet.getId(), newSnippet.getCode(), newSnippet.getTimestamp());  // Logging that the new snippet has been successfully created
		return ResponseEntity.ok(response);  // Returning a HTTP 200 OK status along with the ID of the created snippet
	}

	@GetMapping("/api/code/latest")  // HTTP GET mapping for getting the latest code snippets
	@ResponseBody  // This annotation tells Spring to convert the returned object into HTTP response body.
	public Object getApiLatestCodeSnippets() {
		logger.debug("Fetching latest CodeSnippets via the codeSnippetService");  // Logging the start of the method
		List<CodeSnippet> latestSnippets = List.of(codeSnippetService.getLatestCodeSnippets());  // Calling the service method to get the latest code snippets
		latestSnippets.forEach(snippet -> logger.info("Fetched CodeSnippet.\n Id: {}, Code: {}, Date: {}",
				snippet.getId(), snippet.getCode(), snippet.getTimestamp()));  // Logging each fetched snippet's details
		logger.info("Latest CodeSnippets fetched");  // Logging the successful fetching of snippets.
		return latestSnippets;  // Returning the fetched snippets
	}
}
