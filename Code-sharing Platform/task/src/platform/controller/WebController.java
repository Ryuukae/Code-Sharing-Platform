package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String getCode(Model model) {
		model.addAttribute("codeSnippet", codeSnippetService.getCodeSnippet().getCode());
		return "codeSnippet";
	}
}
