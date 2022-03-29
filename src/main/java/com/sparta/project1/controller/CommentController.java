package com.sparta.project1.controller;

import com.sparta.project1.dto.CommentRequestDto;
import com.sparta.project1.model.Comment;
import com.sparta.project1.repository.ArticleRepository;
import com.sparta.project1.repository.CommentRepository;
import com.sparta.project1.security.UserDetailsImpl;
import com.sparta.project1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 댓글 등록하기
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

    // 댓글 수정하기
    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Long> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.update(id, requestDto);
        requestDto.setWriter(userDetails.getUsername());
        return ResponseEntity.ok(id);
    }

    // 댓글 삭제하기
    @DeleteMapping("/api/comments/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
