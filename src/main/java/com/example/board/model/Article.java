package com.example.board.model;

import com.example.board.common.model.BaseDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "article")
public class Article extends BaseDTO {
  @Transient public static final String SEQUENCE_NAME = "article_sequence";

  @Id private Long id;

  private String title;

  private String content;

  private List<Comment> comment;

  private int views;

  private int likes;

  @CreatedDate private LocalDateTime createdAt;
}
