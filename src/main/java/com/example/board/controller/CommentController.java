package com.example.board.controller;

import com.example.board.service.ArticleService;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
  private final CommentService commentService;
  private final ArticleService articleService;

  @Autowired
  public CommentController(CommentService commentService, ArticleService articleService) {
    this.commentService = commentService;
    this.articleService = articleService;
  }

  @PostMapping(value = "/comment")
  public String commentPost(
      @RequestParam("articleId") Long articleId,
      @RequestParam("content") String content,
      Model model) {
    // 댓글 작성하기
    model.addAttribute("article", commentService.createComment(articleId, content));
    return "articleView";
  }

  @GetMapping(value = "/commentView")
  public String commentView(@RequestParam("id") String id, Model model) {
    // 댓글 작성 페이지 반환
    model.addAttribute("id", id);
    return "/commentView";
  }
}
