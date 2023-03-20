package com.example.board.service;

import static org.assertj.core.api.Assertions.assertThat;import static org.mockito.Mockito.*;

import com.example.board.controller.ArticleForm;
import com.example.board.model.Article;
import com.example.board.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
  @InjectMocks ArticleService articleService;

  @Mock
  ArticleRepository articleRepository;

  @Test
  void articlePost() {
    Article article1 = mock(Article.class);
    lenient().when(article1.getTitle()).thenReturn("hello");
    lenient().when(article1.getContent()).thenReturn("hi");

    ArticleForm articleform = new ArticleForm();
    articleform.setTitle("hello");
    articleform.setContent("hi");
    Article article2 = articleService.createArticle(articleform);

    assertThat(article1.getContent()).isEqualTo(article2.getContent());
  }


  @Test
  void increaseViewCount() {
    Article article1 = mock(Article.class);
    lenient().when(article1.getViews()).thenReturn(1);

    ArticleForm articleform = new ArticleForm();
    Article article2 = articleService.createArticle(articleform);
    article2.setViews(article2.getViews() + 1);
    articleRepository.save(article2);

    assertThat(article1.getViews()).isEqualTo(article2.getViews());
  }

  @Test
  void increaseLikeCount() {
    Article article1 = mock(Article.class);
    lenient().when(article1.getLikes()).thenReturn(1);

    ArticleForm article = new ArticleForm();
    Article article2 = articleService.createArticle(article);
    article2.setLikes(article.getLikes() + 1);
    articleRepository.save(article2);

    assertThat(article1.getLikes()).isEqualTo(article2.getLikes());
  }

  @Test
  void patchArticle() {
    Article article = mock(Article.class);
    lenient().when(article.getTitle()).thenReturn("hello");
    lenient().when(article.getContent()).thenReturn("hi");

    ArticleForm article1 = new ArticleForm();
    article1.setId(300L);

    article1.setTitle("a");
    article1.setContent("b");
    Article articlemock = articleService.createArticle(article1);

    ArticleForm article2 = new ArticleForm();
    article2.setTitle("hello");
    article2.setContent("hi");
    articlemock.setTitle(article2.getTitle());
    articlemock.setContent(article2.getContent());
    articleRepository.save(articlemock);

    assertThat(article.getTitle()).isEqualTo(articlemock.getTitle());
    assertThat(article.getContent()).isEqualTo(articlemock.getContent());

  }


}


