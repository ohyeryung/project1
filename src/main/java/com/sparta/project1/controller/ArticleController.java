package com.sparta.project1.controller;


import com.sparta.project1.domain.Article;
import com.sparta.project1.domain.ArticleRepository;
import com.sparta.project1.domain.ArticleRequestDto;
import com.sparta.project1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

//    // 상세 페이지로 이동 + HomeController 필요 (HTML로 이동하기 위함)
//    @GetMapping("/api/articles/detail{id}")
//    public Optional<Article> go_detail(@PathVariable Long id) {
//        return articleRepository.findById(id);
//    }
//
    // Controller 하나로만 페이지 이동 기능 구현 가능 (ModelAndView)
//    @GetMapping("/api/articles/detail")
//    public ModelAndView MoveDetail(@RequestParam Long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/detail.html");
//        modelAndView.addObject("id", id);
//        return modelAndView;
//    }
    //  Controller 하나로만 페이지 이동 기능 구현 가능 (ModelAndView)
    @RequestMapping("/api/articles/detail")
    public ModelAndView detail(@RequestParam("id") Long id) throws Exception {
            ModelAndView mav = new ModelAndView("/detail.html");
            return mav;
    }

    @GetMapping("/api/articles/{id}")
    public Article goDetail(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        return article;
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){
        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }


}