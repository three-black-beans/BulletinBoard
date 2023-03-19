package com.example.board.controller;

import com.example.board.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

  private final ArticleService articleService;

  @Autowired
  public BoardController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/")
  public String boardList(Model model) {
    model.addAttribute("articleList", articleService.findArticles(1));
    return "board";
  }

  @GetMapping("/board")
  public String boardPageList(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
    model.addAttribute("articleList", articleService.findArticles(page));
    return "board";
  }
}
