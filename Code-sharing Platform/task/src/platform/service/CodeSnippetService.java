package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

@Service
public class CodeSnippetService {
	private final CodeSnippet codeSnippet = new CodeSnippet(
			"""
					   public static void main(String[] args) {
					   SpringApplication.run(CodeSharingPlatform.class, args);
					}"""
	);

	public CodeSnippet getCodeSnippet() {
		return codeSnippet;
	}
}
