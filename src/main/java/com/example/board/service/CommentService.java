package com.example.board.service;

import com.example.board.model.Article;
import com.example.board.model.Comment;
import com.example.board.repository.ArticleRepository;
import com.example.board.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final ArticleService articleService;
  private final ArticleRepository articleRepository;

  public CommentService(
      CommentRepository commentRepository,
      ArticleService articleService,
      ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.articleService = articleService;
    this.articleRepository = articleRepository;
  }

  public Article createComment(Long articleId, String content) {
    // 댓글 작성하기
    Comment comment = new Comment();
    comment.setContent(content);
    Article article = articleService.findArticle(articleId);
    List<Comment> commentList = article.getComment();
    if (commentList == null) {
      commentList = new ArrayList<Comment>();
    }
    commentList.add(comment);
    article.setComment(commentList);
    return articleRepository.save(article);
  }
}
