package platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;
import platform.repositories.CodeSnippetRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
		initializeLastId();
	}

	// Method loads the ID of the last added snippet into memory
	private void initializeLastId() {
		logger.debug("Initializing lastId");
		CodeSnippet lastSnippet = codeSnippetRepository.findTopByOrderByIdDesc();
		if (lastSnippet != null) {
			CodeSnippet.setLastId(new AtomicInteger(lastSnippet.getId()));
			logger.debug("lastId initialized to {}", lastSnippet.getId());
		} else {
			logger.debug("No snippets found, lastId initialized to 0");
		}
		logger.info("LastId initialization completed");
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
		logger.info("CodeSnippet saved successfully, exiting 'saveCodeSnippet' method");
	}

	// Fetch a CodeSnippet by its ID
	public CodeSnippet getCodeSnippetById(int id) {
		logger.info("Entering 'getCodeSnippetById' method");
		logger.debug("Getting code snippet by ID: {}", id);
		CodeSnippet snippet = codeSnippetRepository.findById(id).orElse(null);
		logger.debug("Code snippet found for ID {}: {}", id, snippet);
		logger.info("Exiting 'getCodeSnippetById' method");
		return snippet;
	}

	// Add a new CodeSnippet, save it to the repository and return it with a new ID
	public CodeSnippet addCodeSnippet(CodeSnippet snippet) {
		logger.info("Entering 'addCodeSnippet' method");
		logger.debug("Adding a new code snippet: {}", snippet);
		snippet = codeSnippetRepository.save(snippet);
		logger.info("Added new code snippet with ID: {}", snippet.getId());
		logger.info("Exiting 'addCodeSnippet' method");
		return snippet;
	}

	// Fetch the 10 most recent CodeSnippets
	public List<CodeSnippet> getLatest10CodeSnippets() {
		logger.info("Entering 'getLatest10CodeSnippets' method");
		logger.debug("Getting the latest code snippets");
		List<CodeSnippet> snippets = codeSnippetRepository.findTop10ByOrderByIdDesc(PageRequest.of(0, 10));
		logger.debug("Returning the latest code snippets: {}", snippets);
		logger.info("Exiting 'getLatest10CodeSnippets' method");
		return snippets;
	}
}
