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
		return new ArrayList<>(codeSnippets); // Return a copy to avoid modification outside
	}

	public CodeSnippet getCodeSnippetById(int id) {
		return codeSnippets.stream()
				       .filter(snippet -> snippet.getId() == id)
				       .findFirst()
				       .orElse(null); // Add error handling as necessary
	}

	public void addCodeSnippet(CodeSnippet snippet) {
		codeSnippets.add(snippet);
		logger.info("Added new code snippet with ID: {}", snippet.getId());
	}

	public CodeSnippet[] getLatestCodeSnippets() {
		try {
			logger.debug("Getting latest code snippets");
			CodeSnippet[] snippets = codeSnippets.stream()
					                         .sorted((s1, s2) -> s2.getTimestamp().compareTo(s1.getTimestamp()))
					                         .limit(Math.min(codeSnippets.size(), 10))
					                         .toArray(CodeSnippet[]::new);
			logger.debug("Latest snippets: {}", Arrays.toString(snippets));
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
