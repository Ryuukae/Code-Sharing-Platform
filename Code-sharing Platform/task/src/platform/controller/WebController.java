package platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;
import platform.util.DateUtils;

@Controller
@RequestMapping("/code")
public class WebController {

	private final CodeSnippetService codeSnippetService;

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;
		logger.info("WebController initialized.");
	}

	@GetMapping("/{id}")
	public ModelAndView displayCodeSnippetById(@PathVariable int id, Model model) {
		try {
			CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);

			model.addAttribute("code", snippet.getCode());
			model.addAttribute("timestamp", DateUtils.formatDate(snippet.getTimestamp()));
		} catch (Exception e) {
			logger.error("Error getting code snippet by ID: {}", id, e);
			return null; // Or
			// return new ModelAndView("errorPage");
		}
		return new ModelAndView("codeSnippet");
	}

	@GetMapping("/new")
	public ModelAndView displayNewCodeSnippetForm() {
		// Return the HTML template file as a ModelAndView
		return new ModelAndView("newCodeSnippet");
	}

	@GetMapping("/latest")
	public ModelAndView displayLatestCodeSnippets(Model model) {
		CodeSnippet[] snippets = codeSnippetService.getLatestCodeSnippets();
		model.addAttribute("snippets", snippets);
		return new ModelAndView("latestCodeSnippets");
	}
}
