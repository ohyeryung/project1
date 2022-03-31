package com.sparta.project1.model;

import com.sparta.project1.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Long userId;

    public Comment(CommentRequestDto requestDto, Long userId) {
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.articleId = requestDto.getArticleId();
        this.userId = userId;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
