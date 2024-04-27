package platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;
import platform.repository.CodeSnippetRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeSnippetService {
	// Initialize logger from slf4j
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetService.class);

	// Dependency to work with code snippets in database
	private final CodeSnippetRepository codeSnippetRepository;

	@Autowired
	// Autowiring code snippets repository
	public CodeSnippetService(CodeSnippetRepository codeSnippetRepository) {
		this.codeSnippetRepository = codeSnippetRepository;
		// Initializing ID for the last added code snippet
		logger.info("Initializing CodeSnippetService");
	}


	// Method for fetching all snippets
	public List<CodeSnippet> getAllCodeSnippets() {
		logger.info("Entering 'getAllCodeSnippets' method");
		logger.debug("Getting all code snippets");
		List<CodeSnippet> snippets = codeSnippetRepository.findAll();
		logger.debug("Returning all code snippets: {}", snippets);
		logger.info("Exiting 'getAllCodeSnippets' method");
		return snippets;
	}

	// Save a new CodeSnippet to the repository
	public void saveCodeSnippet(CodeSnippet codeSnippet) {
		logger.info("Entering 'saveCodeSnippet' method, saving: {}", codeSnippet);
		codeSnippetRepository.save(codeSnippet);
		logger.info("CodeSnippet with ID {} saved successfully, exiting 'saveCodeSnippet' method", codeSnippet.getId());
	}

	// Delete a CodeSnippet from the repository
	public void delete(CodeSnippet codeSnippet) {
		logger.info("Entering 'delete' method, deleting: {}", codeSnippet);
		codeSnippetRepository.delete(codeSnippet);
		logger.info("CodeSnippet deleted successfully, exiting 'delete' method");
	}

	// Fetch a CodeSnippet by its ID
	public CodeSnippet getCodeSnippetById(UUID id) {
		logger.info("Entering 'getCodeSnippetById' method");
		logger.debug("Getting code snippet by ID: {}", id);
		Optional<CodeSnippet> snippetOptional = codeSnippetRepository.findById(id);
		if (snippetOptional.isPresent()) {
			logger.debug("Code snippet found for ID {}: {}", id, snippetOptional.get());
			logger.info("Exiting 'getCodeSnippetById' method");
			return snippetOptional.get();
		} else {
			logger.error("Code snippet not found for ID: {}", id);
			return null; // or throw an exception
		}
	}


	// Fetch the 10 most recent CodeSnippets
	public List<CodeSnippet> getLatest10CodeSnippets() {
		logger.info("Entering 'getLatest10CodeSnippets' method");
		logger.info("Getting the latest code snippets");
		PageRequest pageRequest = PageRequest.of(0, 10); // 0 means the first page, 10 is the size of page.
		Page<CodeSnippet> snippets = codeSnippetRepository.findByTimeLessThanEqualAndViewsLessThanEqualOrderByTimestampDesc(0, 0, pageRequest);
		// Now you can retrieve the content from the Page object
		List<CodeSnippet> snippetList = snippets.getContent();
		logger.info("Returning the latest code snippets: {}", snippets);
		logger.info("Exiting 'getLatest10CodeSnippets' method");
		return snippetList;
	}

	public void checkTime(CodeSnippet codeSnippet) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

		// Parse timestamp to LocalDateTime
		LocalDateTime start = LocalDateTime.parse(codeSnippet.getTimestamp(), formatter);

		// Get current LocalDateTime
		LocalDateTime now = LocalDateTime.now();

		// Convert time to a Duration object and add to start
		Duration duration = Duration.ofSeconds(codeSnippet.getTime());
		LocalDateTime combined = start.plus(duration);

		// Check if now has passed the combined time
		if (combined.isAfter(now)) {
			// If now has not passed, update time field to reflect the amount of seconds left
			Duration remaining = Duration.between(now, combined);
			codeSnippet.setTime((int) remaining.getSeconds());
		} else {
			// If now is passed the combined time, delete snippet
			codeSnippetRepository.delete(codeSnippet);
		}

	}


}
