package platform.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CodeSnippetService {
	private static final Logger logger = LoggerFactory.getLogger(CodeSnippetService.class);

	private final List<CodeSnippet> codeSnippets = new ArrayList<>();

	public List<CodeSnippet> getAllCodeSnippets() {
		logger.debug("Getting all code snippets");
		List<CodeSnippet> snippets = new ArrayList<>(codeSnippets);
		logger.debug("Return copy of all code snippets: {}", snippets);
		return snippets;
	}

	public CodeSnippet getCodeSnippetById(int id) {
		logger.debug("Getting code snippet by ID: {}", id);
		CodeSnippet snippet = codeSnippets.stream()
				                      .filter(s -> {
					                      logger.trace("Checking if snipper ID: {} equals to searching ID: {}", s.getId(), id);
					                      return s.getId() == id;
				                      })
				                      .findFirst()
				                      .orElse(null); // Add error handling as necessary
		logger.debug("Code snippet found for ID {}: {}", id, snippet);
		return snippet;
	}

	public void addCodeSnippet(CodeSnippet snippet) {
		logger.debug("Adding a new code snippet: {}", snippet);
		codeSnippets.add(snippet);
		logger.info("Added new code snippet with ID: {}", snippet.getId());
	}

	public CodeSnippet[] getLatestCodeSnippets() {
		try {
			logger.debug("Getting latest code snippets");
			CodeSnippet[] snippets = codeSnippets.stream()
					                         .sorted((s1, s2) -> {
						                         logger.trace("Comparing snippet timestamp: {} with {}", s1.getTimestamp(), s2.getTimestamp());
						                         int timestampComparison = s1.getTimestamp().compareTo(s2.getTimestamp());
						                         if (timestampComparison != 0) {
							                         return timestampComparison;
						                         } else {
							                         return Integer.compare(s2.getId(), s1.getId());
						                         }
					                         })
					                         .limit(Math.min(codeSnippets.size(), 10))
					                         .toArray(CodeSnippet[]::new);
			logger.debug("Latest {} snippets: {}", snippets.length, Arrays.toString(snippets));
			return snippets;
		} catch (NullPointerException e) {
			logger.error("Failed to get latest code snippets due to null pointer", e);
			return new CodeSnippet[ 0 ];
		} catch (Exception e) {
			logger.error("Failed to get latest code snippets for unknown reason", e);
			return new CodeSnippet[ 0 ];
		}
	}
}
