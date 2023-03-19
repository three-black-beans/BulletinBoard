package com.example.board.model;

import com.example.board.repository.ArticleRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.service.SequenceGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentTest {

    @Autowired
    CommentRepository commentRepository;

//    @Test
//    void articlePost() {
//        Article article = new Article();
//        article.setId(sequenceGeneratorService.generateSequence(article.SEQUENCE_NAME));
//        article.setTitle("hi");
//        article.setContent("hello");
//        articleRepository.save(article);
//
//        List<Article> articleList = articleRepository.findAll();
//        articleList.forEach(System.out::println);
//        System.out.println(article.getContent());
//        System.out.println(article.getTitle());
//        System.out.println(article.getId());
//        System.out.println(article.getCreatedAt());
//        assertThat(article.getContent()).isEqualTo(article.getContent());
//    }

}