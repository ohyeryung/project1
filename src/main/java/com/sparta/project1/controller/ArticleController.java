package com.sparta.project1.controller;


import com.sparta.project1.domain.Article;
import com.sparta.project1.domain.ArticleRepository;
import com.sparta.project1.domain.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    @PostMapping("/api/articles")
    public Article createarticles(@RequestBody ArticleRequestDto requestDto) {
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    @GetMapping("/api/articles")
    public List<Article> getarticles() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

}