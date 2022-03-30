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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class CommentController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;


//    댓글 등록 실험
    @PostMapping("/api/detail/{id}/comments")
    public Comment saveComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        System.out.println(userId);
        requestDto.setWriter(userDetails.getUsername());
        Comment comment = new Comment(requestDto, userId);
        System.out.println(comment);
        return commentRepository.save(comment);
    }

//    // 댓글 등록하기
//    @PostMapping("/api/detail/{id}/comments")
//    public Comment saveComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        articleRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("null")
//        );
//        requestDto.setArticleId(id);
//        requestDto.setWriter(userDetails.getUsername());
//        Comment comment = new Comment(requestDto);
//        commentRepository.save(comment);
//        return comment;
//    }

    // 댓글 보여주기
    @GetMapping("/api/detail/{id}/comments")
    public List<Comment> getComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return commentRepository.findAllByArticleId(id);
    }

    // 댓글 수정하기
    @PutMapping("/api/detail/{id}/comments/{id1}")
    public ResponseEntity<Long> updateComment(@PathVariable Long id, @PathVariable Long id1 , @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.update(id1, requestDto);
        requestDto.setWriter(userDetails.getUsername());
        return ResponseEntity.ok(id1);
    }

    // 댓글 삭제하기
    @DeleteMapping("/api/detail/{id}/comments")
    public Long deleteArticle(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
