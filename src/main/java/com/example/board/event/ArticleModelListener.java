package com.example.board.event;

import com.example.board.model.Article;
import com.example.board.service.SequenceGeneratorService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleModelListener extends AbstractMongoEventListener<Article> {

  private SequenceGeneratorService sequenceGenerator;

  @Autowired
  public ArticleModelListener(SequenceGeneratorService sequenceGenerator) {
    this.sequenceGenerator = sequenceGenerator;
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Article> event) {
    if (event.getSource().getId() == null) {
      event.getSource().setId(sequenceGenerator.generateSequence(Article.SEQUENCE_NAME));
      event.getSource().setCreatedAt(LocalDateTime.now());
    }
  }
}
