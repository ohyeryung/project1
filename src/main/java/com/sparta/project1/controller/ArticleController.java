package com.sparta.project1.controller;


import com.sparta.project1.model.Article;
import com.sparta.project1.repository.ArticleRepository;
import com.sparta.project1.dto.ArticleRequestDto;
import com.sparta.project1.security.UserDetailsImpl;
import com.sparta.project1.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Article createarticles(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername());
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    // 게시글 목록 보여주기
    @GetMapping("/api/articles")
    public List<Article> getarticles() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }


    // 상세 페이지
    @GetMapping("/api/detail/{id}")
    public Article goDetail(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    // 게시글 수정하기
    @PutMapping("/api/detail/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername());
        return articleService.update(id, requestDto);
    }

    // 게시글 삭제하기
    @DeleteMapping("/api/detail/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }

//
//    @GetMapping("/api/articles/detail")
//    public ModelAndView go_detail(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return articleService.go_detail(id, userDetails);
//    }

    // Controller 하나로만 페이지 이동 기능 구현 가능 (ModelAndView)
//    @GetMapping("/api/articles/detail")
//    public ModelAndView MoveDetail(@RequestParam Long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/detail.html");
//        modelAndView.addObject("id", id);
//        return modelAndView;
//    }
//    //  Controller 하나로만 페이지 이동 기능 구현 가능 (ModelAndView)
//    @RequestMapping("/api/articles/detail")
//    public ModelAndView detail(@RequestParam("id") Long id) {
//            ModelAndView mav = new ModelAndView("/detail.html");
//            return mav;
//    }
}