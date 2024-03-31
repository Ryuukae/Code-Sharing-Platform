package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@GetMapping(value = "/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CodeSnippet> getApiCodeSnippetById(@PathVariable int id) {
		CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);
		if (snippet == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(snippet);
	}

	// In ApiController.java
	@PostMapping(value = "/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> createCodeSnippet(@RequestBody Map<Object, Object> requestBody) {
		String newCode = (String) requestBody.get("code");
		CodeSnippet newSnippet = new CodeSnippet(newCode);
		codeSnippetService.addCodeSnippet(newSnippet);
		Map<String, String> response = new HashMap<>();
		response.put("id", String.valueOf(newSnippet.getId()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/code/latest", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getLatestApiCodeSnippets() {
		CodeSnippet[] snippets = codeSnippetService.getLatestCodeSnippets();
		List<Map<String, Object>> response = Arrays.stream(snippets)
				                                     .filter(snippet -> snippet != null)
				                                     .map(snippet -> {
					                                     Map<String, Object> res = new HashMap<>();
					                                     res.put("code", snippet.getCode());
					                                     res.put("date", snippet.getTimestamp());
					                                     return res;
				                                     })
				                                     .collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}


}
