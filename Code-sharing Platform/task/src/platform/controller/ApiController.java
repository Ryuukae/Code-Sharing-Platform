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
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final CodeSnippetService codeSnippetService;
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;
		logger.info("CodeSnippetService has been Autowired");
	}

	@GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
	public CodeSnippet getApiCodeSnippet() {
		logger.debug("Getting API code...");
		CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();
		logger.debug("Retrieved API code: {}", codeSnippet.toString());
		return codeSnippet;
	}

	@PostMapping(value = "/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Object, Object>> updateCodeSnippet(@RequestBody Map<String, String> requestBody) {
		String newCode = requestBody.get("code");
		logger.trace(newCode);
		logger.debug("Updating code snippet with: {}", newCode);
		codeSnippetService.updateCodeSnippet(newCode);
		logger.info("Code snippet updated successfully");
		return ResponseEntity.ok().body(new HashMap<>());
	}
}
