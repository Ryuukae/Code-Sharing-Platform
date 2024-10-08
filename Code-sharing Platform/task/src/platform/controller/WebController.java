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
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/code")
public class WebController {

	private final CodeSnippetService codeSnippetService;
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	public WebController(CodeSnippetService codeSnippetService) {
		this.codeSnippetService = codeSnippetService;

		// Logging that the controller has been initialized
		logger.info("WebController initialized.");
	}

	@GetMapping("/{UUID}")
	public ModelAndView displayCodeSnippetById(@PathVariable UUID UUID, Model model) {

		// Logging the beginning of displayCodeSnippetById method
		logger.info("Entered displayCodeSnippetById method");

		try {
			CodeSnippet snippet = codeSnippetService.getCodeSnippetById(UUID);

			// Logging the fetched code snippet and its properties
			logger.info("Fetched code snippet with properties: Code: {}, Timestamp: {}.", snippet.getCode(), snippet.getTimestamp());

			model.addAttribute("code", snippet.getCode());
			model.addAttribute("timestamp", snippet.getTimestamp());

			// Logging the end of displayCodeSnippetById method
			logger.info("Exiting displayCodeSnippetById method");

			return new ModelAndView("codeSnippetView");
		} catch (Exception e) {
			logger.error("Error getting code snippet by ID: {}", UUID, e);
			return null;
		}

	}

	@GetMapping("/new")
	public ModelAndView displayNewCodeSnippetForm() {

		// Logging the beginning of displayNewCodeSnippetForm method
		logger.info("Entered displayNewCodeSnippetForm method");

		// Logging the end of displayNewCodeSnippetForm method
		logger.info("Exiting displayNewCodeSnippetForm method");

		return new ModelAndView("newCodeSnippetView");
	}

	@GetMapping(value = "/latest")
	public ModelAndView displayLatestWebCodeSnippets(Model model) {

		// Logging the beginning of displayLatestWebCodeSnippets method
		logger.info("Entered displayLatestWebCodeSnippets method");

		try {
			List<CodeSnippet> latestSnippets = codeSnippetService.getLatest10CodeSnippets();

			if (latestSnippets == null || latestSnippets.isEmpty()) {
				logger.warn("No code latestSnippets found");
				return new ModelAndView("errorPage");
			}

			// Logging each fetched Snippet
			latestSnippets.forEach(snippet -> logger.info("Fetched code snippet with the following properties:\nId: {},\nCode: {}," +
					                                              "\nTimestamp: {}," +
					                                              "\nTime: {},\nViews: {}", snippet.getId(), snippet.getCode(), snippet.getTimestamp(), snippet.getTime(), snippet.getViews()));


			// Debug logging for fetched Snippets
			logger.debug("Snippets: {}", latestSnippets);

			List<CodeSnippetDto> snippetDtos = latestSnippets.stream()
					                                   .map(snippet -> new CodeSnippetDto(snippet.getId(), snippet.getCode(),
							                                   snippet.getTimestamp(), snippet.getTime(), snippet.getViews()))
					                                   .collect(Collectors.toList());

			// Logging each created DTO object
			snippetDtos.forEach(snippetDto -> logger.info("Created code snippetDto DTO object with the following properties:\nId: {},\nCode: " +
					                                              "{},\nTimestamp: {}," +
					                                              "\nTime: {},\nViews: {}", snippetDto.getId(),
					snippetDto.getCodeSnippet(), snippetDto.getTimestamp(), snippetDto.getTime(), snippetDto.getViews()));


			// Debug logging for created DTOs
			logger.debug("SnippetDtos: {}", snippetDtos);

			model.addAttribute("snippetDtos", snippetDtos);

			// Logging the successful end of the method
			logger.info("Exiting displayLatestWebCodeSnippets method with success");
			return new ModelAndView("latestCodeSnippetsView");

		} catch (Exception e) {
			// Logging the exception occurred
			logger.error("Error while retrieving latest code snippets", e);

			// Logging the unsuccessful end of the method
			logger.info("Exiting displayLatestWebCodeSnippets method with error");

			return new ModelAndView("errorPage");
		} finally {
			// Logging the end of displayLatestWebCodeSnippets method
			logger.info("Exiting displayLatestWebCodeSnippets method");
		}
	}
}
