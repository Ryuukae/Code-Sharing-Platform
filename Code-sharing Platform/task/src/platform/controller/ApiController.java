package platform.controller;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.dto.CodeSnippetDto;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetService;

import java.util.*;

// This is a REST Controller Class
@RestController
public class ApiController {

	// Service for handling code snippets
	private final CodeSnippetService codeSnippetService;

	// Logger for this class
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	// Constructor-based Dependency Injection for CodeSnippetService
	@Autowired
	public ApiController(CodeSnippetService codeService) {
		this.codeSnippetService = codeService;
		// Info log to indicate successful Autowiring
		logger.info("CodeSnippetService has been Autowired");
	}

	// Get mapping for accessing a specific code snippet by ID
	@GetMapping(value = "/api/code/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getApiCodeSnippetById(@PathVariable String id) {
		try {
			UUID uuid = UUID.fromString(id);
			CodeSnippet snippet = codeSnippetService.getCodeSnippetById(uuid);


			logger.debug("Entering getApiCodeSnippetById method...");
			logger.debug("Getting CodeSnippet with id {}...", id);
			//Checking whether the snippet instance is null
			if (snippet == null) {
				logger.error("CodeSnippet with id {} not found", id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CodeSnippet with id: " + id + " not found");
			}

			int views = snippet.getViews();
			if (views > 1) {
				snippet.setViews(views - 1);
				codeSnippetService.saveCodeSnippet(snippet);
				logger.info("Decreased view count for CodeSnippet with id {}", id);
			} else if (views == 1) {
				codeSnippetService.delete(snippet);
				logger.info("Deleted CodeSnippet with id {} as view count reached 0", id);
			}

			codeSnippetService.checkTime(snippet);
			logger.info("Exiting getApiCodeSnippetById method...");

			return ResponseEntity.ok(snippet);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid UUID format: {}", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID format: " + id);
		}
	}


	// Post mapping for creating a new code snippet
	@PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> createCodeSnippet(@RequestBody Map<Object, Object> requestBody) {
		// Extracting code from request body
		String newCode = (String) requestBody.get("code");
		try {


			int time = (int) requestBody.get("time");
			int views = (int) requestBody.get("views");

			// Debug log indicating start of creating new code snippet
			logger.debug("Creating new CodeSnippet...");

			// Creating new code snippet and saving it using the service
			CodeSnippet newSnippet = new CodeSnippet(newCode, time, views);
			codeSnippetService.saveCodeSnippet(newSnippet);

			// Preparing response with the id of the newly created snippet
			Map<Object, Object> response = new HashMap<>();
			response.put("id", String.valueOf(newSnippet.getId()));
			return ResponseEntity.ok(response);
		} catch (NumberFormatException e) {
			logger.error("Invalid time or views format: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>());
		}
	}

	@GetMapping("/api/code/latest")
	public ResponseEntity<List<CodeSnippetDto>> displayLatestAPICodeSnippets() {
		logger.info("Fetching latest CodeSnippets via the codeSnippetService");
		try {
			List<CodeSnippet> latestSnippets = codeSnippetService.getLatest10CodeSnippets();
			if (latestSnippets == null) {
				logger.error("Error occurred while fetching latest code snippets -- NullPointerException: latestSnippets is null");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			List<CodeSnippetDto> response = new ArrayList<>();
			for (CodeSnippet snippet : latestSnippets) {
				response.add(new CodeSnippetDto(snippet.getId(), snippet.getCode(), snippet.getTimestamp(), snippet.getTime(), snippet.getViews()));
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			logger.error("Error occurred while fetching latest code snippets -- DataAccessException", e);
			System.out.println("Error occurred while fetching latest code snippets -- DataAccessException: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ServiceException e) {
			logger.error("Error occurred while fetching latest code snippets -- ServiceException", e);
			System.out.println("Error occurred while fetching latest code snippets -- ServiceException: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
