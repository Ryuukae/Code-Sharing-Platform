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

@RestController
public class ApiController {

	private final CodeSnippetService codeSnippetService;
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;
		logger.info("CodeSnippetService has been Autowired");
	}

	@GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CodeSnippet> getApiCodeSnippetById(@PathVariable int id) {
		logger.debug("Start getting CodeSnippet with id {}", id);
		CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);
		if (snippet == null) {
			logger.error("CodeSnippet with id {} not found", id);
			return ResponseEntity.notFound().build();
		}
		logger.info("CodeSnippet with id {} has been fetched successfully. Code: {}, Date: {}",
				id, snippet.getCode(), snippet.getTimestamp());
		return ResponseEntity.ok(snippet);
	}

	@PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> createCodeSnippet(@RequestBody Map<Object, Object> requestBody) {
		String newCode = (String) requestBody.get("code");
		logger.debug("Creating new CodeSnippet with code {}", newCode);
		CodeSnippet newSnippet = new CodeSnippet(newCode);
		codeSnippetService.saveCodeSnippet(newSnippet);
		Map<String, String> response = new HashMap<>();
		response.put("id", String.valueOf(newSnippet.getId()));
		logger.info("New CodeSnippet with id {} has been added. Code: {}, Date: {}",
				newSnippet.getId(), newSnippet.getCode(), newSnippet.getTimestamp());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/api/code/latest")
	@ResponseBody
	public Object getApiLatestCodeSnippets() {
		logger.debug("Fetching latest CodeSnippets via the codeSnippetService");
		List<CodeSnippet> latestSnippets = codeSnippetService.getLatest10CodeSnippets();  // updated to new method
		latestSnippets.forEach(snippet -> logger.info("Fetched CodeSnippet.\n Id: {}, Code: {}, Date: {}",
				snippet.getId(), snippet.getCode(), snippet.getTimestamp()));
		logger.info("Latest CodeSnippets fetched");
		return latestSnippets;
	}
}
