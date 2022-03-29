package com.sparta.project1.controller;

import com.sparta.project1.dto.CommentRequestDto;
import com.sparta.project1.model.Comment;
import com.sparta.project1.repository.ArticleRepository;
import com.sparta.project1.repository.CommentRepository;
import com.sparta.project1.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/api/details/{id}/comments")
    public Comment saveComment(@PathVariable("id") Long articleId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        articleRepository.findById(articleId).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        requestDto.setArticleId(articleId);
        requestDto.setWriter(userDetails.getUsername());
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment;
    }
}
