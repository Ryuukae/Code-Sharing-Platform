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
	public ResponseEntity<Object> updateCodeSnippet(@RequestBody String newCode) {
		logger.debug("Updating code snippet with: {}", newCode);
		codeSnippetService.updateCodeSnippet(newCode);
		logger.info("Code snippet updated successfully");
		return ResponseEntity.ok().body(new HashMap<>());
	}
}
