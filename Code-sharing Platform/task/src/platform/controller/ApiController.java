package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

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
		CodeSnippet cs = codeSnippetService.getCodeSnippet();
		logger.debug("Retrieved API code: {}", cs.toString());
		return cs;
	}

	@PostMapping("/code/new")
	public void updateCodeSnippet(@RequestBody String newCode) {
		logger.debug("Updating code snippet with: {}", newCode);
		codeSnippetService.updateCodeSnippet(newCode);
		logger.info("Code snippet updated successfully");
	}
}
