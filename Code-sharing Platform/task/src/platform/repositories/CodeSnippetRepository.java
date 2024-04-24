package platform.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeSnippet;

import java.util.List;

public interface CodeSnippetRepository extends JpaRepository<CodeSnippet, Integer> {

	List<CodeSnippet> findTop10ByOrderByIdDesc(Pageable pageable);

	CodeSnippet findByTimestamp(String timestamp);

	CodeSnippet findByCodeSnippet(String codeSnippet); // changed this line

	CodeSnippet findTopByOrderByIdDesc();
}
