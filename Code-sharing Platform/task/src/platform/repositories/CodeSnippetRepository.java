package platform.repositories;

// Importing the required libraries

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeSnippet;

import java.util.List;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Integer> {
	// Logger using slf4j
	Logger logger = LoggerFactory.getLogger(CodeSnippetRepository.class);

	// Method to fetch the top 10 CodeSnippets by their ID in descending order
	List<CodeSnippet> findTop10ByOrderByIdDesc(Pageable pageable);

	// Method to find a CodeSnippet based on its timestamp
	CodeSnippet findByTimestamp(String timestamp);

	// Method to find a CodeSnippet based on its codeSnippet string
	CodeSnippet findByCodeSnippet(String codeSnippet);

	// Method to fetch the top CodeSnippet by their ID in descending order
	CodeSnippet findTopByOrderByIdDesc();
}
