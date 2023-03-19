package com.example.board.service;

import com.example.board.controller.ArticleForm;
import com.example.board.model.Article;
import com.example.board.repository.ArticleRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public Article createArticle(ArticleForm articleForm) {
    // 게시글 생성 method
    Article article = new Article();
    article.setTitle(articleForm.getTitle());
    article.setContent(articleForm.getContent());
    articleRepository.save(article);
    return article;
  }

  public Article patchArticle(ArticleForm articleForm) {
    Article article = findArticle(articleForm.getId());
    article.setTitle(articleForm.getTitle());
    article.setContent(articleForm.getContent());
    articleRepository.save(article);
    return article;
  }

  public void deleteArticle(ArticleForm articleForm) {
    articleRepository.deleteById(articleForm.getId());
  }

  public Article increaseLike(ArticleForm articleForm) {
    // 좋아요 수 증가시키기
    Article article = findArticle(articleForm.getId());
    article.setLikes(article.getLikes() + 1);
    articleRepository.save(article);
    return article;
  }

  public Article increaseView(Long articleId) {
    // 조회수 증가시키기
    Article article = findArticle(articleId);
    article.setViews(article.getViews() + 1);
    articleRepository.save(article);

    return article;
  }

  public Article findArticle(Long id) {
    // 게시글 아이디로 게시글을 찾는 method
    Optional<Article> articleTemp = articleRepository.findById(id);
    if (articleTemp.isEmpty()) {
      throw new NoSuchElementException("No value present");
    }
    return articleTemp.get();
  }

  public List<Article> findArticles(int page) {
    // 모든 게시글을 찾아 5개씩 페이지별로 반환해주는 method
    if (page - 1 < 0) {
      throw new IllegalArgumentException("잘못된 값 요청입니다.");
    }
    Pageable paging = PageRequest.of(page - 1, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
    Page<Article> articlePage = articleRepository.findAll(paging);
    List<Article> articleList = articlePage.getContent();
    return articleList;
  }

  public int findArticleNum() {
    // 모든 시글을 찾아 5개씩 페이지별로 반환해주는 method
    List<Article> articleList = articleRepository.findAll();
    return articleList.size();
  }
}
