package com.example.board.repository;

import com.example.board.model.Article;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
  @Override
  Page<Article> findAll(Pageable pageable);

  Optional<Article> findById(Long id);

  void deleteById(Long id);
}
