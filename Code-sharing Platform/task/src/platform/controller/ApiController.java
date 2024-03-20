package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

@RestController
@RequestMapping("/api")
public class ApiController {

	private final CodeSnippetService codeSnippetService;

	@Autowired
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;
	}

	@GetMapping(value = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
	public CodeSnippet getApiCode() {
		return codeSnippetService.getCodeSnippet();
	}
}
