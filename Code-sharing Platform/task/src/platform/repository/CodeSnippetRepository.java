package platform.repository;

// Importing the required libraries

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeSnippet;

import java.util.List;
import java.util.UUID;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, UUID> {
	// Logger using slf4j
	Logger logger = LoggerFactory.getLogger(CodeSnippetRepository.class);

	// Method to fetch the top 10 CodeSnippets by their ID in descending order
	Page<CodeSnippet> findTop10ByOrderByIdDesc(Pageable pageable);

	// Method to find a CodeSnippet based on its timestamp
	List<CodeSnippet> findByTimestamp(String timestamp);

	// Method to find a CodeSnippet based on its codeSnippet string
	List<CodeSnippet> findByCodeSnippet(String codeSnippet);

	// Method to fetch the top CodeSnippet by their ID in descending order
	List<CodeSnippet> findTopByOrderByIdDesc(Pageable pageable);


	List<CodeSnippet> findAllByOrderByIdDesc();

	Page<CodeSnippet> findTop10ByOrderByTimestampDesc(Pageable pageable);


	Page<CodeSnippet> findByTimeLessThanEqualAndViewsLessThanEqualOrderByTimestampDesc(int time, int views, Pageable pageable);

}
