package service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Document;

public interface DocumentService extends JpaRepository<Document, Long>{
	Document findDocumentById(Long id);
	List<Document> findDocumentsByName(String name);
	List<Document> findDocumentByActiveTrue();
}
