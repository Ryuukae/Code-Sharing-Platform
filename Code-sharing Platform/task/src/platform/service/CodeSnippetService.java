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
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetService.class);

	private final CodeSnippetRepository codeSnippetRepository;

	@Autowired
	public CodeSnippetService(CodeSnippetRepository codeSnippetRepository) {
		this.codeSnippetRepository = codeSnippetRepository;
		initializeLastId();
	}

	private void initializeLastId() {
		logger.debug("Initializing lastId");
		CodeSnippet lastSnippet = codeSnippetRepository.findTopByOrderByIdDesc();
		if (lastSnippet != null) {
			CodeSnippet.setLastId(new AtomicInteger(lastSnippet.getId()));
			logger.debug("lastId initialized to {}", lastSnippet.getId());
		} else {
			logger.debug("No snippets found, lastId initialized to 0");
		}
	}

	public List<CodeSnippet> getAllCodeSnippets() {
		logger.debug("Getting all code snippets");
		List<CodeSnippet> snippets = codeSnippetRepository.findAll();
		logger.debug("Returning all code snippets: {}", snippets);
		return snippets;
	}

	public void saveCodeSnippet(CodeSnippet codeSnippet) {
		codeSnippetRepository.save(codeSnippet);
	}

	public CodeSnippet getCodeSnippetById(int id) {
		logger.debug("Getting code snippet by ID: {}", id);
		CodeSnippet snippet = codeSnippetRepository.findById(id).orElse(null); // Add error handling as necessary
		logger.debug("Code snippet found for ID {}: {}", id, snippet);
		return snippet;
	}

	public CodeSnippet addCodeSnippet(CodeSnippet snippet) {
		logger.debug("Adding a new code snippet: {}", snippet);
		snippet = codeSnippetRepository.save(snippet);
		logger.info("Added new code snippet with ID: {}", snippet.getId());
		return snippet;
	}

	// The method for getting latest snippets might require a custom query
	// This needs to be further clarified as per requirements

	public List<CodeSnippet> getLatest10CodeSnippets() {
		logger.debug("Getting the latest code snippets");
		List<CodeSnippet> snippets = codeSnippetRepository.findTop10ByOrderByIdDesc(PageRequest.of(0, 10));
		logger.debug("Returning the latest code snippets: {}", snippets);
		return snippets;
	}

}
