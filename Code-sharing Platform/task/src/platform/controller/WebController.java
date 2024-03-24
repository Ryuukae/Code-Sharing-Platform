package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;
import platform.util.DateUtils;

@Controller
@RequestMapping("")
public class WebController {

	private final CodeSnippetService codeSnippetService;

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;
		logger.info("WebController initialized.");
	}

	@GetMapping("/code")
	public ModelAndView displayCodeSnippet(Model model) {
		// Retrieve a code snippet using the codeSnippetService
		CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();

		// Set the code snippet and timestamp in the model
		model.addAttribute("code", codeSnippet.getCode());
		model.addAttribute("timestamp", DateUtils.formatDate(codeSnippet.getTimestamp()));

		// Return the HTML template file as a ModelAndView
		return new ModelAndView("codeSnippet");
	}

	@GetMapping("/code/new")
	public ModelAndView displayNewCodeSnippetForm() {
		// Return the HTML template file as a ModelAndView
		return new ModelAndView("newCodeSnippet");
	}
}
