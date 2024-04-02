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
import platform.dto.CodeSnippetDto;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
			model.addAttribute("timestamp", snippet.getTimestamp());
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

	@GetMapping(value = "/latest")
	public ModelAndView displayLatestWebCodeSnippets(Model model) {
		try {
			CodeSnippet[] snippets = codeSnippetService.getLatestCodeSnippets();

			if (snippets == null || snippets.length == 0) {
				logger.warn("No code snippets found");
				return new ModelAndView("errorPage");
			}

			logger.debug("Snippets: {}", Arrays.toString(snippets));

			List<CodeSnippetDto> snippetDtos = Arrays.stream(snippets)
					                                   .map(snippet -> new CodeSnippetDto(snippet.getCode(), snippet.getTimestamp()))
					                                   .collect(Collectors.toList());

			logger.debug("SnippetDtos: {}", snippetDtos);

			model.addAttribute("snippetDtos", snippetDtos);
			return new ModelAndView("latestCodeSnippets");

		} catch (Exception e) {
			logger.error("Error while retrieving latest code snippets", e);
			return new ModelAndView("errorPage");
		}
	}
}
