package platform.controller;

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

	@Autowired
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;
	}

	@GetMapping("/code")
	public ResponseEntity<String> getCodePage() {
		CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();
		String html = "<html><head><title>Code</title></head><body><pre>" + codeSnippet.getCode() + "</pre></body></html>";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<>(html, headers, HttpStatus.OK);
	}
}
