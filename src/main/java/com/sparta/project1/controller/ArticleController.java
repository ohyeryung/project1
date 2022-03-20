package com.sparta.project1.controller;


import com.sparta.project1.domain.Article;
import com.sparta.project1.domain.ArticleRepository;
import com.sparta.project1.domain.ArticleRequestDto;
import com.sparta.project1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    //게시글 저장하기
    @PostMapping("/api/articles")
    public Article createarticles(@RequestBody ArticleRequestDto requestDto) {
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    // 게시글 목록 보여주기
    @GetMapping("/api/articles")
    public List<Article> getarticles() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    // 상세 페이지로 이동
    @GetMapping("/api/articles/{id}")
    public Optional<Article> go_detail(@PathVariable Long id) {
        return articleRepository.findById(id);
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){
        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }


}