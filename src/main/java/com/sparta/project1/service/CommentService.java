package com.sparta.project1.service;

import com.sparta.project1.dto.CommentRequestDto;
import com.sparta.project1.model.Comment;
import com.sparta.project1.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
        public Long update(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 댓글입니다."));

       comment.update(requestDto);
       return comment.getId();
    }
}
