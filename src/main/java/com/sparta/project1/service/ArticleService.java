package com.sparta.project1.service;

import com.sparta.project1.domain.Article;
import com.sparta.project1.domain.ArticleRepository;
import com.sparta.project1.domain.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
//
////    @Transactional
//    public static ArticleRequestDto searchById(Long id) {
//        Article entity = articleRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
//        );
//        return new ArticleRequestDto(entity);
//    }

    @Transactional
    public Long update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        article.update(requestDto);
        return article.getId();
    }
}
