package com.example.board.controller;

import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {
  private final ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping(value = "/article")
  public String articlePost(ArticleForm articleForm) {
    // 게시글 생성하기
    articleService.createArticle(articleForm);
    return "redirect:/";
  }

  @PatchMapping(value = "/article")
  public String articlePatch(ArticleForm articleForm, Model model) {
    // 게시글 수정하기
    Article article = articleService.patchArticle(articleForm);
    model.addAttribute("article", article);
    return "articleView";
  }

  @DeleteMapping(value = "/article")
  public String articleDelete(ArticleForm articleForm) {
    // 게시글 삭제하기
    articleService.deleteArticle(articleForm);
    return "redirect:/";
  }

  @PostMapping(value = "/article/like")
  public String articleLike(ArticleForm articleForm, Model model) {
    // 게시글 좋아요
    Article article = articleService.increaseLike(articleForm);
    model.addAttribute("article", article);
    return "articleView";
  }

  @GetMapping(value = "/articleView")
  public String articleView(@RequestParam("id") Long articleId, Model model) {
    // 게시글 상세 보기 페이지 반환
    Article article = articleService.increaseView(articleId);
    model.addAttribute("article", article);
    return "articleView";
  }

  @GetMapping(value = "/article/post")
  public String articleCreateView() {
    // 게시글 작성 페이지 반환
    return "articleCreateView";
  }

  @GetMapping(value = "/article/patch")
  public String articlePatchView(@RequestParam("id") Long articleId, Model model) {
    // 게시글 수정 페이지 반환
    model.addAttribute("article", articleService.findArticle(articleId));
    return "articlePatchView";
  }
}
