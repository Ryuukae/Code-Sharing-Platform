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

import java.util.List;
import java.util.stream.Collectors;

@Controller  // Marks this class as a Spring MVC Controller
@RequestMapping("/code")  // All mappings in this controller are relative to /code
public class WebController {

	private final CodeSnippetService codeSnippetService;

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);  // Logger to log messages for this class

	@Autowired  // Autowiring the service required to carry out controller operations
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;
		logger.info("WebController initialized.");  // Logging the initiation of controller
	}

	@GetMapping("/{id}")  // Maps HTTP GET /code/{id} to this method
	public ModelAndView displayCodeSnippetById(@PathVariable int id, Model model) {
		logger.info("Entered displayCodeSnippetById method");  // Logging the entry into the function
		try {
			CodeSnippet snippet = codeSnippetService.getCodeSnippetById(id);  // Fetching the code snippet by given id

			logger.info("Fetched code snippet with properties: Code: {}, Timestamp: {}.", snippet.getCode(), snippet.getTimestamp()); // Logging the fetch result

			model.addAttribute("code", snippet.getCode());  // Adding fetched code to model
			model.addAttribute("timestamp", snippet.getTimestamp());  // Adding fetched timestamp to model
		} catch (Exception e) {
			logger.error("Error getting code snippet by ID: {}", id, e);
			return null; // Error handling - return null or return new ModelAndView("errorPage");
		}
		logger.info("Exiting displayCodeSnippetById method");  // Logging the exit from the function
		return new ModelAndView("codeSnippet");  // Return a render-able view as response
	}

	@GetMapping("/new")  // Maps HTTP GET /code/new to this method
	public ModelAndView displayNewCodeSnippetForm() {
		logger.info("Entered displayNewCodeSnippetForm method");
		// The below line returns a template for client to input a new code snippet.
		logger.info("Exiting displayNewCodeSnippetForm method");
		return new ModelAndView("newCodeSnippet");
	}

	@GetMapping(value = "/latest")  // Maps HTTP GET /code/latest to this method
	public ModelAndView displayLatestWebCodeSnippets(Model model) {
		logger.info("Entered displayLatestWebCodeSnippets method");
		try {
			List<CodeSnippet> snippets = codeSnippetService.getLatest10CodeSnippets();

			if (snippets == null || snippets.isEmpty()) {  // Checking if any snippets were found
				logger.warn("No code snippets found");  // Logging warning, if no snippets were found
				return new ModelAndView("errorPage");  // Respond with error page on no snippets found
			}

			snippets.forEach(snippet -> logger.info("Fetched code snippet with properties: Code: {}, Timestamp: {}.", snippet.getCode(), snippet.getTimestamp()));  // Log fetched snippets

			logger.debug("Snippets: {}", snippets); // Log the collection of fetched snippets

			// Converting the code snippets into a transportable data model
			List<CodeSnippetDto> snippetDtos = snippets.stream()
					                                   .map(snippet -> new CodeSnippetDto(snippet.getCode(), snippet.getTimestamp()))
					                                   .collect(Collectors.toList());

			snippetDtos.forEach(snippetDto -> logger.info("Fetched code snippet DTO with properties: Code: {}, Timestamp: {}.",
					snippetDto.getCode(), snippetDto.getTimestamp()));  // Logging the DTO's

			logger.debug("SnippetDtos: {}", snippetDtos);  // Log all the DTO's

			model.addAttribute("snippetDtos", snippetDtos);  // Adding the list of DTO's to the model
			return new ModelAndView("latestCodeSnippetsView");  // Return a render-able view as response

		} catch (Exception e) {
			logger.error("Error while retrieving latest code snippets", e);  // Logging any exception that occurred
			return new ModelAndView("errorPage");  // Respond with an error page on exception
		} finally {
			logger.info("Exiting displayLatestWebCodeSnippets method");  // Logging the exit from the function
		}
	}
}
