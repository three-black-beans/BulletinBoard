package com.example.board.controller;

import com.example.board.model.Comment;
import lombok.Getter;
import lombok.Setter;import java.util.List;

@Getter
@Setter
public class ArticleForm {
  private Long id;
  private String title;
  private String content;
}
